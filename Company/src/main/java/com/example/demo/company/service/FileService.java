package com.example.demo.company.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);
}