package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.FarmTransaction;
import com.example.demo.agriculture.enums.TransactionType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FarmTransactionRepository
        extends JpaRepository<FarmTransaction, Long> 
{

    List<FarmTransaction> findByFarmId(Long farmId);

    List<FarmTransaction>
    findByTransactionDateBetween(
            LocalDate from,
            LocalDate to);

    List<FarmTransaction>
    findByType(TransactionType type);
}