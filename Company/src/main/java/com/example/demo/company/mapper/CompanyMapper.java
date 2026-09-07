package com.example.demo.company.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.entity.Company;

@Component
public class CompanyMapper {

    public Company toEntity(CompanyRequestDto dto) {

        return Company.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public CompanyResponseDto toResponseDto(Company company) {

        return CompanyResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .email(company.getEmail())
                .version(company.getVersion())
                .createdDate(company.getCreatedDate())
                .createdBy(company.getCreatedBy())
                .updatedDate(company.getUpdatedDate())
                .updatedBy(company.getUpdatedBy())
                .build();
    }

    public void updateEntity(
            Company company,
            CompanyRequestDto dto) {

        company.setName(dto.getName());

        company.setEmail(dto.getEmail());

        if (dto.getPassword() != null
                && !dto.getPassword().isBlank()) {

            company.setPassword(dto.getPassword());
        }
    }
}