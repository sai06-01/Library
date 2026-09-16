package com.example.demo.agriculture.mapper;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.entity.*;

import org.springframework.stereotype.Component;

@Component
public class AgricultureMapper 
{

    public FarmResponseDto toFarmDto(Farm farm) 
    {

        FarmResponseDto dto =
                new FarmResponseDto();

        dto.setId(farm.getId());
        dto.setFarmCode(farm.getFarmCode());
        dto.setFarmName(farm.getFarmName());
        dto.setOwnerName(farm.getOwnerName());
        dto.setLandArea(farm.getLandArea());
        dto.setPrimaryCrop(farm.getPrimaryCrop());
        dto.setStatus(farm.getStatus());
        dto.setLocation(farm.getLocation());
        dto.setDescription(farm.getDescription());
        dto.setCreatedAt(farm.getCreatedAt());
        dto.setUpdatedAt(farm.getUpdatedAt());
        dto.setVersion(farm.getVersion());

        return dto;
    }

    public CropResponseDto toCropDto(Crop crop) {

        CropResponseDto dto =
                new CropResponseDto();

        dto.setId(crop.getId());
        dto.setCropName(crop.getCropName());
        dto.setCropType(crop.getCropType());
        dto.setPlantingDate(crop.getPlantingDate());
        dto.setExpectedHarvestDate(
                crop.getExpectedHarvestDate());
        dto.setExpectedYield(
                crop.getExpectedYield());
        dto.setStatus(crop.getStatus());
        dto.setFarmId(crop.getFarm().getId());

        return dto;
    }

    public FarmTaskResponseDto toTaskDto(
            FarmTask task) {

        FarmTaskResponseDto dto =
                new FarmTaskResponseDto();

        dto.setId(task.getId());
        dto.setTaskName(task.getTaskName());
        dto.setDescription(task.getDescription());
        dto.setTaskDate(task.getTaskDate());
        dto.setStatus(task.getStatus());
        dto.setAssignedTo(task.getAssignedTo());
        dto.setFarmId(task.getFarm().getId());

        return dto;
    }

    public FarmTransactionResponseDto
    toTransactionDto(FarmTransaction transaction) {

        FarmTransactionResponseDto dto =
                new FarmTransactionResponseDto();

        dto.setId(transaction.getId());
        dto.setTransactionDate(
                transaction.getTransactionDate());
        dto.setType(transaction.getType());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(
                transaction.getDescription());
        dto.setStatus(transaction.getStatus());
        dto.setFarmId(transaction.getFarm().getId());

        return dto;
    }

    public InventoryResponseDto
    toInventoryDto(InventoryItem item) 
    {

        InventoryResponseDto dto =new InventoryResponseDto();

        dto.setId(item.getId());
        dto.setItemName(item.getItemName());
        dto.setCategory(item.getCategory());
        dto.setQuantity(item.getQuantity());
        dto.setUnit(item.getUnit());
        dto.setReorderLevel(item.getReorderLevel());
        dto.setFarmId(item.getFarm().getId());

        return dto;
    }
}