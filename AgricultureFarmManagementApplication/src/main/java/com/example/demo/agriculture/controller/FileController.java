package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.service.FileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
@Tag(name = "File APIs")
public class FileController 
{

    private final FileService service;

    public FileController(FileService service)
    { 
    	this.service = service;
    }

    @Operation(summary = "Upload agriculture file")
    @PostMapping("/upload")
    public ResponseEntity<String>
    upload(@RequestParam("file") MultipartFile file) 
    {

        String name =service.upload(file);

        return ResponseEntity.ok("File uploaded successfully: "+ name);
    }

    @Operation(summary = "Download agriculture file")
    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]>
    download(@PathVariable String fileName) 
    {
    	byte[] data =  service.download(fileName);
        return ResponseEntity.ok().header( HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ fileName+ "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}