package com.example.demo.company.controller;

import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company-profile")
public class CompanyProfileController {

    private final CompanyService companyService;

    public CompanyProfileController(
            CompanyService companyService) {

        this.companyService = companyService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<CompanyResponseDto> getProfile(
            @PathVariable String name) {

        return ResponseEntity.ok(
                companyService.getProfile(name)
        );
    }
    
}