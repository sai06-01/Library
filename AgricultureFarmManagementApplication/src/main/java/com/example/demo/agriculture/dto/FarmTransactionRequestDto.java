package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.TransactionStatus;
import com.example.demo.agriculture.enums.TransactionType;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class FarmTransactionRequestDto 
{

    @NotNull
    private LocalDate transactionDate;

    @NotNull
    private TransactionType type;

    @NotNull
    @Positive
    private Double amount;

    private String description;

    @NotNull
    private TransactionStatus status;

    @NotNull
    @Positive
    private Long farmId;

    public LocalDate getTransactionDate()
    {
        return transactionDate;
    }

    public TransactionType getType()
    {
        return type;
    }

    public Double getAmount()
    {
        return amount;
    }

    public String getDescription()
    {
        return description;
    }

    public TransactionStatus getStatus() 
    {
        return status;
    }

    public Long getFarmId() 
    {
        return farmId;
    }

    public void setTransactionDate(LocalDate transactionDate) 
    {
        this.transactionDate = transactionDate;
    }

    public void setType(TransactionType type)
    {
        this.type = type;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setStatus(TransactionStatus status) 
    {
        this.status = status;
    }

    public void setFarmId(Long farmId) 
    {
        this.farmId = farmId;
    }
}