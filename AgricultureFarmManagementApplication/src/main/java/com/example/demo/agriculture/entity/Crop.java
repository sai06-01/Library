package com.example.demo.agriculture.entity;

import com.example.demo.agriculture.enums.CropStatus;
import com.example.demo.agriculture.enums.CropType;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "crops")
public class Crop
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cropName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CropType cropType;

    @Column(nullable = false)
    private LocalDate plantingDate;

    private LocalDate expectedHarvestDate;

    private Double expectedYield;

    @Enumerated(EnumType.STRING)
    private CropStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public Crop() {
    }

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

    public Farm getFarm()
    {
        return farm;
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

    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }
}