package com.example.demo.agriculture.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory_items")
public class InventoryItem 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Double quantity;

    @Column(nullable = false)
    private String unit;

    private Double reorderLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public InventoryItem() {
    }

    public Long getId() 
    {
        return id;
    }

    public String getItemName()
    {
        return itemName;
    }

    public String getCategory()
    {
        return category;
    }

    public Double getQuantity()
    {
        return quantity;
    }

    public String getUnit()
    {
        return unit;
    }

    public Double getReorderLevel() 
    {
        return reorderLevel;
    }

    public Farm getFarm() 
    {
        return farm;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public void setReorderLevel(Double reorderLevel)
    {
        this.reorderLevel = reorderLevel;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}