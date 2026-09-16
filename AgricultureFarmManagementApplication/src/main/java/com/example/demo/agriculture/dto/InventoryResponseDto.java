package com.example.demo.agriculture.dto;

public class InventoryResponseDto 
{

    private Long id;
    private String itemName;
    private String category;
    private Double quantity;
    private String unit;
    private Double reorderLevel;
    private Long farmId;

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

    public Long getFarmId() 
    {
        return farmId;
    }

    public void setId(Long id)
    {
        this.id = id;
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

    public void setFarmId(Long farmId)
    {
        this.farmId = farmId;
    }
}