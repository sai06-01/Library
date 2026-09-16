package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.entity.*;
import com.example.demo.agriculture.exception.FarmNotFoundException;
import com.example.demo.agriculture.mapper.AgricultureMapper;
import com.example.demo.agriculture.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class FarmTransactionServiceImpl
        implements FarmTransactionService 
        {

    private final FarmTransactionRepository repository;
    private final FarmRepository farmRepository;
    private final AgricultureMapper mapper;

    public FarmTransactionServiceImpl(
            FarmTransactionRepository repository,
            FarmRepository farmRepository,
            AgricultureMapper mapper) 
    {

        this.repository = repository;
        this.farmRepository = farmRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public FarmTransactionResponseDto create(FarmTransactionRequestDto dto) 
    {

        Farm farm = farmRepository.findById(dto.getFarmId())
                        .orElseThrow(() ->
                                new FarmNotFoundException( "Farm not found"));

        FarmTransaction transaction = new FarmTransaction();

        transaction.setTransactionDate(dto.getTransactionDate());

        transaction.setType(dto.getType());

        transaction.setAmount(dto.getAmount());

        transaction.setDescription(dto.getDescription());

        transaction.setStatus( dto.getStatus());

        transaction.setFarm(farm);

        return mapper.toTransactionDto(
                repository.save(transaction));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FarmTransactionResponseDto>
    getByFarm(Long farmId) 
    {

        return repository
                .findByFarmId(farmId)
                .stream()
                .map(mapper::toTransactionDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FarmTransactionResponseDto>
    getByDateRange(
            LocalDate from,
            LocalDate to) 
    {

        return repository
                .findByTransactionDateBetween(from,to)
                .stream()
                .map(mapper::toTransactionDto)
                .toList();
    }
}