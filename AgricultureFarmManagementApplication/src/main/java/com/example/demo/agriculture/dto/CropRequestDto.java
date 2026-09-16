package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.CropStatus;
import com.example.demo.agriculture.enums.CropType;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class CropRequestDto 
{

    @NotBlank
    private String cropName;

    @NotNull
    private CropType cropType;

    @NotNull
    private LocalDate plantingDate;

    private LocalDate expectedHarvestDate;

    @Positive
    private Double expectedYield;

    @NotNull
    private CropStatus status;

    @NotNull
    @Positive
    private Long farmId;

    public String getCropName()
    {
        return cropName;
    }

    public CropType getCropType()
    {
        return cropType;
    }

    public LocalDate getPlantingDate() 
    {
        return plantingDate;
    }

    public LocalDate getExpectedHarvestDate() 
    {
        return expectedHarvestDate;
    }

    public Double getExpectedYield()
    {
        return expectedYield;
    }

    public CropStatus getStatus()
    {
        return status;
    }

    public Long getFarmId()
    {
        return farmId;
    }

    public void setCropName(String cropName) 
    {
        this.cropName = cropName;
    }

    public void setCropType(CropType cropType)
    {
        this.cropType = cropType;
    }

    public void setPlantingDate(LocalDate plantingDate) 
    {
        this.plantingDate = plantingDate;
    }

    public void setExpectedHarvestDate(LocalDate expectedHarvestDate)
    {
        this.expectedHarvestDate = expectedHarvestDate;
    }

    public void setExpectedYield(Double expectedYield)
    {
        this.expectedYield = expectedYield;
    }

    public void setStatus(CropStatus status)
    {
        this.status = status;
    }

    public void setFarmId(Long farmId)
    {
        this.farmId = farmId;
    }
}