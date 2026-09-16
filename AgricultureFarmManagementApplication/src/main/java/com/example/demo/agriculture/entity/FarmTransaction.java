package com.example.demo.agriculture.entity;

import com.example.demo.agriculture.enums.TransactionStatus;
import com.example.demo.agriculture.enums.TransactionType;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "farm_transactions")
public class FarmTransaction 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private Double amount;

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public FarmTransaction() {
    }

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

    public Farm getFarm() 
    {
        return farm;
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

    public void setFarm(Farm farm) 
    {
        this.farm = farm;
    }
}