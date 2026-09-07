package com.example.demo.company.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.service.CompanyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    /*
     * This value comes from:
     *
     * application-dev.properties
     * application-test.properties
     * application-prod.properties
     *
     * depending on the active profile.
     */
    @Value("${app.message:Company Application}")
    private String profileMessage;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createOrUpdate(
            @Valid @RequestBody CompanyRequestDto requestDto) {

        CompanyResponseDto response =
                companyService.createOrUpdate(requestDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                companyService.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<Page<CompanyResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                companyService.getAll(
                        page,
                        size,
                        sortBy,
                        direction
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id) {

        companyService.deleteById(id);

        return ResponseEntity.ok(
                "Company deleted successfully"
        );
    }

    /*
     * Spring Profiles demonstration
     */
    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {

        return ResponseEntity.ok(profileMessage);
    }
}