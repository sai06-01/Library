package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;

import java.util.List;

public interface FarmTaskService 
{

    FarmTaskResponseDto create( FarmTaskRequestDto dto);

    FarmTaskResponseDto get(Long id);

    List<FarmTaskResponseDto>
    getByFarm(Long farmId);

    FarmTaskResponseDto update(Long id,FarmTaskRequestDto dto);

    void delete(Long id);
}