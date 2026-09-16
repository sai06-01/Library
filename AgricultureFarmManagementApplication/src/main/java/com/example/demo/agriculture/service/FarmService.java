package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.FarmRequestDto;
import com.example.demo.agriculture.dto.FarmResponseDto;
import com.example.demo.agriculture.enums.FarmStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FarmService 
{

    FarmResponseDto createFarm( FarmRequestDto dto);

    FarmResponseDto getFarm(Long id);

    Page<FarmResponseDto> getAllFarms(Pageable pageable);

    FarmResponseDto updateFarm(
            Long id,
            FarmRequestDto dto);

    void deleteFarm(Long id);

    List<FarmResponseDto>
    getFarmsByStatus(FarmStatus status);

    List<FarmResponseDto>
    getFarmsByLocation(String location);

    long countFarms();

    void generateReport();
}