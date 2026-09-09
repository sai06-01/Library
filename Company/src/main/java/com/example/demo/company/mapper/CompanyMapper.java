package com.example.demo.company.mapper;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDto request) {

        Company company = new Company();

        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());
        company.setRole(request.getRole());
        company.setStatus(request.getStatus());

        return company;
    }

    public CompanyResponseDto toResponseDto(Company company) {

        CompanyResponseDto response = new CompanyResponseDto();

        response.setId(company.getId());
        response.setName(company.getName());
        response.setEmail(company.getEmail());
        response.setRole(company.getRole());
        response.setStatus(company.getStatus());
        response.setVersion(company.getVersion());
        response.setCreatedAt(company.getCreatedAt());
        response.setUpdatedAt(company.getUpdatedAt());

        return response;
    }

    public void updateEntity(
            Company company,
            CompanyRequestDto request) {

        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setPassword(request.getPassword());
        company.setRole(request.getRole());
        company.setStatus(request.getStatus());
    }
}