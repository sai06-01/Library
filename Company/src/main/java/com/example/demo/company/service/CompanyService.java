package com.example.demo.company.service;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;

import java.util.List;

public interface CompanyService {

    CompanyResponseDto createCompany(CompanyRequestDto request);

    List<CompanyResponseDto> getAllCompanies();

    CompanyResponseDto getCompanyById(Long id);

    CompanyResponseDto updateCompany(
            Long id,
            CompanyRequestDto request);

    void deleteCompany(Long id);

    CompanyResponseDto getProfile(String name);
}