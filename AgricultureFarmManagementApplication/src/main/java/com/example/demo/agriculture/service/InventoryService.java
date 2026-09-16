package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;

import java.util.List;

public interface InventoryService 
{

    InventoryResponseDto create(InventoryRequestDto dto);

    List<InventoryResponseDto>
    getByFarm(Long farmId);

    List<InventoryResponseDto>
    getLowStock();

    InventoryResponseDto update(
            Long id,
            InventoryRequestDto dto);

    void delete(Long id);
}