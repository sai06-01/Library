package com.example.demo.company.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.dto.LoginRequestDto;
import com.example.demo.company.dto.LoginResponseDto;

import com.example.demo.company.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Tag(
        name = "Company API",
        description = "Company Management APIs"
)
public class CompanyController {

    private final CompanyService service;


    // CREATE
    @PostMapping
    @Operation(
            summary = "Create Company",
            description = "Create a new company"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Company created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error"
            )
    })
    public ResponseEntity<CompanyResponseDto>
    createCompany(
            @Valid
            @RequestBody
            CompanyRequestDto dto) {

        CompanyResponseDto response =
                service.createCompany(dto);

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }


    // GET BY ID
    @GetMapping("/{id}")
    @Operation(
            summary = "Get Company By ID",
            description = "Get company by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Company found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Company not found"
            )
    })
    public ResponseEntity<CompanyResponseDto>
    getCompanyById(

            @Parameter(
                    description = "Company ID",
                    required = true
            )
            @PathVariable
            Long id) {

        return ResponseEntity.ok(
                service.getCompanyById(id)
        );
    }


    // GET ALL
    @GetMapping("/all")
    @Operation(
            summary = "Get All Companies",
            description = "Get all companies"
    )
    public ResponseEntity<List<CompanyResponseDto>>
    getAllCompanies() {

        return ResponseEntity.ok(
                service.getAllCompanies()
        );
    }


    // PAGINATION + SORTING
    @GetMapping("/page")
    @Operation(
            summary = "Get Companies With Pagination and Sorting",
            description =
                    "Get companies using pagination and sorting"
    )
    public ResponseEntity<Page<CompanyResponseDto>>
    getCompanies(

            @Parameter(
                    description =
                            "Page number. Starts from 0"
            )
            @RequestParam(
                    defaultValue = "0"
            )
            int page,

            @Parameter(
                    description =
                            "Number of records per page"
            )
            @RequestParam(
                    defaultValue = "5"
            )
            int size,

            @Parameter(
                    description =
                            "Field used for sorting"
            )
            @RequestParam(
                    defaultValue = "id"
            )
            String sortBy,

            @Parameter(
                    description =
                            "Sorting direction: asc or desc"
            )
            @RequestParam(
                    defaultValue = "asc"
            )
            String sortDirection) {

        Page<CompanyResponseDto> response =
                service.getCompanies(
                        page,
                        size,
                        sortBy,
                        sortDirection
                );

        return ResponseEntity.ok(response);
    }


    // UPDATE
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Company",
            description = "Update company details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =
                            "Company updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description =
                            "Company not found"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description =
                            "Optimistic locking conflict"
            )
    })
    public ResponseEntity<CompanyResponseDto>
    updateCompany(

            @PathVariable
            Long id,

            @Valid
            @RequestBody
            CompanyRequestDto dto) {

        CompanyResponseDto response =
                service.updateCompany(
                        id,
                        dto
                );

        return ResponseEntity.ok(response);
    }


    // DELETE
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Company",
            description = "Delete company by ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =
                            "Company deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description =
                            "Company not found"
            )
    })
    public ResponseEntity<String>
    deleteCompany(
            @PathVariable Long id) {

        service.deleteCompany(id);

        return ResponseEntity.ok(
                "Company deleted successfully"
        );
    }


    // LOGIN
    @PostMapping("/login")
    @Operation(
            summary = "Company Login",
            description =
                    "Check company email and password"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description =
                            "Login successful"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description =
                            "Invalid email or password"
            )
    })
    public ResponseEntity<LoginResponseDto>
    login(

            @Valid
            @RequestBody
            LoginRequestDto dto) {

        LoginResponseDto response =
                service.login(dto);

        return ResponseEntity.ok(response);
    }
}