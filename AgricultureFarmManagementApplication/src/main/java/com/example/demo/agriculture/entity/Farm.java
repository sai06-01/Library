package com.example.demo.agriculture.entity;

import com.example.demo.agriculture.enums.CropType;
import com.example.demo.agriculture.enums.FarmStatus;

import jakarta.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "farms")
@EntityListeners(AuditingEntityListener.class)
public class Farm 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String farmCode;

    @Column(nullable = false)
    private String farmName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false)
    private Double landArea;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CropType primaryCrop;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FarmStatus status;

    @Column(nullable = false)
    private String location;

    private String description;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    @OneToMany(
            mappedBy = "farm",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Crop> crops = new ArrayList<>();

    @OneToMany(
            mappedBy = "farm",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FarmTask> tasks = new ArrayList<>();

    @OneToMany(
            mappedBy = "farm",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FarmTransaction> transactions =
            new ArrayList<>();

    @OneToMany(
            mappedBy = "farm",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InventoryItem> inventoryItems =
            new ArrayList<>();

    public Farm() {
    }

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

    public List<Crop> getCrops()
    {
        return crops;
    }

    public List<FarmTask> getTasks() {
        return tasks;
    }

    public List<FarmTransaction> getTransactions() {
        return transactions;
    }

    public List<InventoryItem> getInventoryItems() {
        return inventoryItems;
    }

    public void setFarmCode(String farmCode) {
        this.farmCode = farmCode;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setLandArea(Double landArea) {
        this.landArea = landArea;
    }

    public void setPrimaryCrop(CropType primaryCrop) {
        this.primaryCrop = primaryCrop;
    }

    public void setStatus(FarmStatus status) {
        this.status = status;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(Long id) {
        this.id = id;
    }
}