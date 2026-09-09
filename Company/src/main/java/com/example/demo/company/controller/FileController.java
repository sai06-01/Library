package com.example.demo.company.controller;

import com.example.demo.company.service.FileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        String fileName =
                fileService.uploadFile(file);

        return ResponseEntity.ok(
                "File uploaded successfully: " + fileName
        );
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(
            @PathVariable String fileName) {

        byte[] data =
                fileService.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +
                                fileName + "\""
                )
                .contentType(
                        MediaType.APPLICATION_OCTET_STREAM
                )
                .body(data);
    }
}