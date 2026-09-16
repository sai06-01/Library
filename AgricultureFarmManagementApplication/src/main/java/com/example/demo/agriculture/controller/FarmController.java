package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.enums.FarmStatus;
import com.example.demo.agriculture.service.FarmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@Tag(name = "Farm APIs",
description = "Manage agriculture farms")
public class FarmController 
{

    private final FarmService service;

    public FarmController(FarmService service) 
    {

        this.service = service;
    }
    @Operation(summary = "Create farm")
    @PostMapping
    public ResponseEntity<FarmResponseDto>create(@Valid@RequestBody FarmRequestDto dto)
    {

        return ResponseEntity .status(HttpStatus.CREATED)
                .body(service.createFarm(dto));
    }

    @Operation(summary = "Get farm by ID")
    @GetMapping("/{id}")
    public ResponseEntity<FarmResponseDto>
    get(@Parameter(description = "Farm ID")
    @PathVariable Long id)
    {

        return ResponseEntity.ok( service.getFarm(id));
    }

    @Operation(summary = "Get all farms with pagination and sorting")
    @GetMapping
    public ResponseEntity<Page<FarmResponseDto>>
    getAll(@Parameter(description = "Page number starting from 0")
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(description = "Number of records per page")
            @RequestParam(defaultValue = "5")
            int size,

            @Parameter(description = "Field to sort by")
            @RequestParam(defaultValue = "id")
            String sortBy,

            @Parameter(description = "asc or desc")
            @RequestParam(defaultValue = "asc")String direction)
    {

        Sort sort =
                "desc".equalsIgnoreCase(direction)
                        ? Sort.by(sortBy).descending()
                        : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(
                        Math.max(page, 0),
                        Math.min(
                        		Math.max(size, 1),100),sort);

        return ResponseEntity.ok(service.getAllFarms(pageable));
    }

    @Operation(summary = "Update farm")
    @PutMapping("/{id}")
    public ResponseEntity<FarmResponseDto>
    update(@PathVariable Long id, @Valid@RequestBody FarmRequestDto dto) {

        return ResponseEntity.ok(service.updateFarm(id, dto));
    }

    @Operation(summary = "Delete farm")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    delete(@PathVariable Long id) 
    {

        service.deleteFarm(id);

        return ResponseEntity.ok("Farm deleted successfully");
    }

    @Operation(summary = "Search farms by status")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<FarmResponseDto>>
    byStatus(@PathVariable FarmStatus status)
    {

        return ResponseEntity.ok(service.getFarmsByStatus(status));
    }

    @Operation(summary = "Search farms by location")
    @GetMapping("/location/{location}")
    public ResponseEntity< List<FarmResponseDto>>
    byLocation(@PathVariable String location) {

        return ResponseEntity.ok(service.getFarmsByLocation(location));
    }
}