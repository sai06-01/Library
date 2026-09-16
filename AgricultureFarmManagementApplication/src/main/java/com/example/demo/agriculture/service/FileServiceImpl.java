package com.example.demo.agriculture.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileServiceImpl
        implements FileService
        {

    private final Path uploadDirectory =Paths.get("uploads")
                    .toAbsolutePath()
                    .normalize();

    public FileServiceImpl() throws IOException
    {

        Files.createDirectories(uploadDirectory);
    }

    @Override
    public String upload(MultipartFile file) 
    {

        if (file == null ||file.isEmpty())
        {

            throw new IllegalArgumentException("File cannot be empty");
        }

        try {

            String originalName =file.getOriginalFilename();

            String fileName = Paths.get(originalName == null? "upload": originalName)
                            .getFileName()
                            .toString();

            Path target =uploadDirectory
                            .resolve(fileName)
                            .normalize();

            if (!target.startsWith(uploadDirectory)) 
            {

                throw new IllegalArgumentException("Invalid file name");
            }

            Files.copy(file.getInputStream(),
                    target,
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        }
        catch (IOException ex) 
        {

            throw new RuntimeException("Unable to upload file",ex);
        }
    }

    @Override
    public byte[] download(String fileName) 
    {

        try
        {

            Path target =uploadDirectory.resolve(fileName)
                            .normalize();

            if (!target.startsWith(
                    uploadDirectory)) 
            {

                throw new IllegalArgumentException("Invalid file name");
            }

            return Files.readAllBytes(target);

        } 
        catch (IOException ex)
        {

            throw new RuntimeException("Unable to download file",ex);
        }
    }
}