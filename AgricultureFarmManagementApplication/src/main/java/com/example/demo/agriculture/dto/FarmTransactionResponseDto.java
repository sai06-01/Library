package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.TransactionStatus;
import com.example.demo.agriculture.enums.TransactionType;

import java.time.LocalDate;

public class FarmTransactionResponseDto 
{

    private Long id;
    private LocalDate transactionDate;
    private TransactionType type;
    private Double amount;
    private String description;
    private TransactionStatus status;
    private Long farmId;

    public Long getId()
    {
        return id;
    }

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

    public void setId(Long id)
    {
        this.id = id;
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