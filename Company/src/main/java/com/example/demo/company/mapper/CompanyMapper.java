package com.example.demo.company.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.entity.Company;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDto dto) {

        Company company = new Company();

        company.setName(dto.getName());
        company.setEmail(dto.getEmail());
        company.setPassword(dto.getPassword());

        return company;
    }

    public void updateEntity(
            CompanyRequestDto dto,
            Company company) {

        company.setName(dto.getName());
        company.setEmail(dto.getEmail());
        company.setPassword(dto.getPassword());
    }

    public CompanyResponseDto toResponseDto(
            Company company) {

        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getEmail(),
                company.getVersion(),
                company.getCreatedDate(),
                company.getCreatedBy(),
                company.getUpdatedDate(),
                company.getUpdatedBy()
        );
    }
}