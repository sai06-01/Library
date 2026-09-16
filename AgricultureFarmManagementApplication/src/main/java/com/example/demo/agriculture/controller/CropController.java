package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.CropRequestDto;
import com.example.demo.agriculture.dto.CropResponseDto;
import com.example.demo.agriculture.service.CropService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crops")
@Tag(name = "Crop Management")
public class CropController 
{

    private final CropService cropService;

    public CropController(CropService cropService) 
    {
        this.cropService = cropService;
    }

    @PostMapping
    @Operation(summary = "Create Crop",
    description = "Creates a new crop")
    public ResponseEntity<CropResponseDto> createCrop( @Valid @RequestBody CropRequestDto request)
    {

        return ResponseEntity.status(HttpStatus.CREATED)
        		.body(cropService.createCrop(request));
    }

    @GetMapping
    @Operation( summary = "Get All Crops",
            description = "Gets all crops with pagination and sorting")
    public ResponseEntity<Page<CropResponseDto>> getAllCrops(@Parameter(description = "Page number")
            @RequestParam(defaultValue = "0")
            int page,

            @Parameter(description = "Page size")
            @RequestParam(defaultValue = "5")
            int size,

            @Parameter(description = "Sort field")
            @RequestParam(defaultValue = "id")
            String sortBy,

            @Parameter(description = "asc or desc")
            @RequestParam(defaultValue = "asc")
            String direction)
    {

        return ResponseEntity.ok(
                cropService.getAllCrops(
                        page,
                        size,
                        sortBy,
                        direction));
    }

    @GetMapping("/{id}")
    @Operation( summary = "Get Crop By ID")
    public ResponseEntity<CropResponseDto> getCropById(
            @Parameter(description = "Crop ID")
            @PathVariable Long id) 
    {

        return ResponseEntity.ok(
                cropService.getCropById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Crop")
    public ResponseEntity<CropResponseDto> updateCrop(@PathVariable Long id,@Valid @RequestBody CropRequestDto request) 
    {

        return ResponseEntity.ok(cropService.updateCrop(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Crop")
    public ResponseEntity<String> deleteCrop(@PathVariable Long id) 
    {

        cropService.deleteCrop(id);

        return ResponseEntity.ok("Crop deleted successfully");
    }
}