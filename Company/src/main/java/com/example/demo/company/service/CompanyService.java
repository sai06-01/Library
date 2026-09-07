package com.example.demo.company.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.dto.LoginRequestDto;
import com.example.demo.company.dto.LoginResponseDto;

public interface CompanyService {

    CompanyResponseDto createCompany(
            CompanyRequestDto dto );

    CompanyResponseDto getCompanyById(
            Long id );

    List<CompanyResponseDto> getAllCompanies();

    Page<CompanyResponseDto> getCompanies(
            int page,
            int size,
            String sortBy,
            String sortDirection);

    CompanyResponseDto updateCompany(
            Long id,
            CompanyRequestDto dto );

    void deleteCompany(Long id);

    LoginResponseDto login(
            LoginRequestDto dto );
}