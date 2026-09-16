package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Tag(name = "Inventory APIs")
public class InventoryController 
{

    private final InventoryService service;

    public InventoryController(InventoryService service)
    {
    this.service = service;
    }

    @Operation(summary = "Add inventory item")@PostMapping
    public ResponseEntity<InventoryResponseDto>
    create(@Valid @RequestBody InventoryRequestDto dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @Operation(summary = "Get inventory for farm")
    @GetMapping("/farm/{farmId}")
    public ResponseEntity<
            List<InventoryResponseDto>>
    byFarm(@PathVariable Long farmId)
    {

        return ResponseEntity.ok(
                service.getByFarm(farmId));
    }

    @Operation(summary = "Get low stock items")
    @GetMapping("/low-stock")
    public ResponseEntity<List<InventoryResponseDto>>
    lowStock() 
    {

        return ResponseEntity.ok(service.getLowStock());
    }

    @Operation(summary = "Update inventory")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDto>
    update(@PathVariable Long id,@Valid @RequestBody InventoryRequestDto dto)
    {

        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Delete inventory item")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    delete(@PathVariable Long id) 
    {
    service.delete(id);
        return ResponseEntity.ok("Inventory item deleted successfully");
    }
}