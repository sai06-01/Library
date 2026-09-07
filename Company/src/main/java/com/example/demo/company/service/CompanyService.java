package com.example.demo.company.service;

import org.springframework.data.domain.Page;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;

public interface CompanyService {

    CompanyResponseDto createOrUpdate(CompanyRequestDto requestDto);

    CompanyResponseDto getById(Long id);

    Page<CompanyResponseDto> getAll(
            int page,
            int size,
            String sortBy,
            String direction);

    void deleteById(Long id);
}