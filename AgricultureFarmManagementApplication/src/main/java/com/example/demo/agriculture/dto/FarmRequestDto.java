package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.CropType;
import com.example.demo.agriculture.enums.FarmStatus;

import jakarta.validation.constraints.*;

public class FarmRequestDto 
{

    @NotBlank(message = "Farm code is required")
    @Size(min = 3, max = 30)
    private String farmCode;

    @NotBlank(message = "Farm name is required")
    @Size(min = 3, max = 100)
    private String farmName;

    @NotBlank(message = "Owner name is required")
    private String ownerName;

    @NotNull(message = "Land area is required")
    @Positive(message = "Land area must be positive")
    private Double landArea;

    @NotNull(message = "Primary crop is required")
    private CropType primaryCrop;

    @NotNull(message = "Farm status is required")
    private FarmStatus status;

    @NotBlank(message = "Location is required")
    private String location;

    @Size(max = 500)
    private String description;

    public String getFarmCode()
    {
        return farmCode;
    }

    public String getFarmName()
    {
        return farmName;
    }

    public String getOwnerName()
    {
        return ownerName;
    }

    public Double getLandArea()
    {
        return landArea;
    }

    public CropType getPrimaryCrop()
    {
        return primaryCrop;
    }

    public FarmStatus getStatus()
    {
        return status;
    }

    public String getLocation() 
    {
        return location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setFarmCode(String farmCode)
    {
        this.farmCode = farmCode;
    }

    public void setFarmName(String farmName)
    {
        this.farmName = farmName;
    }

    public void setOwnerName(String ownerName)
    {
        this.ownerName = ownerName;
    }

    public void setLandArea(Double landArea)
    {
        this.landArea = landArea;
    }

    public void setPrimaryCrop(CropType primaryCrop)
    {
        this.primaryCrop = primaryCrop;
    }

    public void setStatus(FarmStatus status)
    {
        this.status = status;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}