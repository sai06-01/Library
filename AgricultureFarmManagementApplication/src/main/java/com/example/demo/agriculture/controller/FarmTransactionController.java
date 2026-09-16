package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.service.FarmTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@Tag(name = "Farm Transaction APIs")
public class FarmTransactionController 
{

    private final FarmTransactionService service;

    public FarmTransactionController(FarmTransactionService service) 
    {

        this.service = service;
    }

    @Operation(summary = "Create farm transaction")
    @PostMapping
    public ResponseEntity<FarmTransactionResponseDto>
    create(@Valid @RequestBody FarmTransactionRequestDto dto)
    {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @Operation(summary = "Get transactions by farm")
    @GetMapping("/farm/{farmId}")
    public ResponseEntity<
            List<FarmTransactionResponseDto>>
    byFarm(@PathVariable Long farmId) 
    {

        return ResponseEntity.ok(
                service.getByFarm(farmId));
    }

    @Operation(summary = "Filter transactions by date")
    @GetMapping("/report")
    public ResponseEntity<List<FarmTransactionResponseDto>>
    report(@RequestParam LocalDate from,@RequestParam LocalDate to)
    {

        return ResponseEntity.ok(service.getByDateRange(from,to));
    }
}