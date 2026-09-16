package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface FarmTransactionService 
{

    FarmTransactionResponseDto create(
            FarmTransactionRequestDto dto);

    List<FarmTransactionResponseDto>
    getByFarm(Long farmId);

    List<FarmTransactionResponseDto>
    getByDateRange(
            LocalDate from,
            LocalDate to);
}