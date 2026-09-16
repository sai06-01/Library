package com.example.demo.agriculture.dto;

import jakarta.validation.constraints.*;

public class InventoryRequestDto 
{

    @NotBlank
    private String itemName;

    @NotBlank
    private String category;

    @NotNull
    @PositiveOrZero
    private Double quantity;

    @NotBlank
    private String unit;

    @PositiveOrZero
    private Double reorderLevel;

    @NotNull
    @Positive
    private Long farmId;

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