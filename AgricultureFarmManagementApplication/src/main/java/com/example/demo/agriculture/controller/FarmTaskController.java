package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.service.FarmTaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Farm Task APIs")
public class FarmTaskController 
{

    private final FarmTaskService service;

    public FarmTaskController(FarmTaskService service) 
    {

        this.service = service;
    }

    @Operation(summary = "Create farm task")
    @PostMapping
    public ResponseEntity<FarmTaskResponseDto>
    create(@Valid @RequestBody FarmTaskRequestDto dto)
    {
    	return ResponseEntity
    			.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @Operation(summary = "Get task")
    @GetMapping("/{id}")
    public ResponseEntity<FarmTaskResponseDto>
    get(@PathVariable Long id) 
    {

        return ResponseEntity.ok(
                service.get(id));
    }

    @Operation(summary = "Get tasks by farm")
    @GetMapping("/farm/{farmId}")
    public ResponseEntity<
            List<FarmTaskResponseDto>>
    getByFarm(@PathVariable Long farmId) {

        return ResponseEntity.ok(
                service.getByFarm(farmId));
    }

    @Operation(summary = "Update task")
    @PutMapping("/{id}")
    public ResponseEntity<FarmTaskResponseDto>
    update(@PathVariable Long id,@Valid
            @RequestBody FarmTaskRequestDto dto)
    {

        return ResponseEntity.ok(
                service.update(id, dto));
    }

    @Operation(summary = "Delete task")
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    delete(@PathVariable Long id)
    {

        service.delete(id);

        return ResponseEntity.ok(
                "Task deleted successfully");
    }
}