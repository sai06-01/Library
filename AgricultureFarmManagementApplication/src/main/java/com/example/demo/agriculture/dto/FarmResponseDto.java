package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.CropType;
import com.example.demo.agriculture.enums.FarmStatus;

import java.time.LocalDateTime;

public class FarmResponseDto
{

    private Long id;
    private String farmCode;
    private String farmName;
    private String ownerName;
    private Double landArea;
    private CropType primaryCrop;
    private FarmStatus status;
    private String location;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long version;

    public Long getId()
    {
        return id;
    }

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

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() 
    {
        return updatedAt;
    }

    public Long getVersion() 
    {
        return version;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public void setVersion(Long version)
    {
        this.version = version;
    }
}