package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.CropStatus;
import com.example.demo.agriculture.enums.CropType;

import java.time.LocalDate;

public class CropResponseDto 
{

    private Long id;
    private String cropName;
    private CropType cropType;
    private LocalDate plantingDate;
    private LocalDate expectedHarvestDate;
    private Double expectedYield;
    private CropStatus status;
    private Long farmId;

    public Long getId()
    {
        return id;
    }

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

    public void setId(Long id) 
    {
        this.id = id;
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