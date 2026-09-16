package com.example.demo.agriculture.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService 
{

    String upload(MultipartFile file);

    byte[] download(String fileName);
}