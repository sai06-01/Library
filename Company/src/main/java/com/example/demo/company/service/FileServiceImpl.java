package com.example.demo.company.service;

import com.example.demo.company.logger.CompanyLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final CompanyLogger logger;

    @Value("${company.upload-dir:uploads}")
    private String uploadDirectory;

    public FileServiceImpl(CompanyLogger logger) {
        this.logger = logger;
    }

    @Override
    public String uploadFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "File cannot be empty"
            );
        }

        try {

            Path directory =
                    Paths.get(uploadDirectory)
                            .toAbsolutePath()
                            .normalize();

            Files.createDirectories(directory);

            String originalName =
                    file.getOriginalFilename();

            if (originalName == null ||
                    originalName.isBlank()) {

                throw new IllegalArgumentException(
                        "Invalid file name"
                );
            }

            String cleanName =
                    Paths.get(originalName)
                            .getFileName()
                            .toString();

            String fileName =
                    UUID.randomUUID()
                            + "_"
                            + cleanName;

            Path target =
                    directory.resolve(fileName)
                            .normalize();

            if (!target.startsWith(directory)) {
                throw new IllegalArgumentException(
                        "Invalid file path"
                );
            }

            Files.copy(
                    file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING
            );

            logger.info(
                    "File uploaded successfully: "
                            + fileName
            );

            return fileName;

        } catch (IOException exception) {

            logger.error(
                    "File upload failed: "
                            + exception.getMessage()
            );

            throw new RuntimeException(
                    "File upload failed",
                    exception
            );
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {

        try {

            Path directory =
                    Paths.get(uploadDirectory)
                            .toAbsolutePath()
                            .normalize();

            Path file =
                    directory.resolve(fileName)
                            .normalize();

            if (!file.startsWith(directory)) {
                throw new IllegalArgumentException(
                        "Invalid file path"
                );
            }

            if (!Files.exists(file)) {
                throw new IllegalArgumentException(
                        "File not found: " + fileName
                );
            }

            return Files.readAllBytes(file);

        } catch (IOException exception) {

            logger.error(
                    "File download failed: "
                            + exception.getMessage()
            );

            throw new RuntimeException(
                    "File download failed",
                    exception
            );
        }
    }
}