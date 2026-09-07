package com.example.demo.company.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.entity.Company;
import com.example.demo.company.exception.CompanyNotFoundException;
import com.example.demo.company.logger.CompanyLogger;
import com.example.demo.company.mapper.CompanyMapper;
import com.example.demo.company.repository.CompanyRepository;

import jakarta.transaction.Transactional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyLogger companyLogger;

    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            CompanyMapper companyMapper,
            CompanyLogger companyLogger) {

        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyLogger = companyLogger;
    }

    @Override
    @Transactional
    public CompanyResponseDto createOrUpdate(
            CompanyRequestDto requestDto) {

        Company company;

        // CREATE
        if (requestDto.getId() == null) {

            companyLogger.info("Creating new company");

            company = companyMapper.toEntity(requestDto);

        } else {

            // UPDATE
            companyLogger.info(
                    "Updating company with id: "
                            + requestDto.getId());

            company = companyRepository
                    .findById(requestDto.getId())
                    .orElseThrow(() ->
                            new CompanyNotFoundException(
                                    "Company not found with id: "
                                            + requestDto.getId()));

            companyMapper.updateEntity(
                    requestDto,
                    company);
        }

        Company savedCompany =
                companyRepository.save(company);

        companyLogger.info(
                "Company saved successfully");

        return companyMapper.toResponseDto(savedCompany);
    }

    @Override
    public CompanyResponseDto getById(Long id) {

        companyLogger.info(
                "Fetching company with id: " + id);

        Company company = companyRepository
                .findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found with id: "
                                        + id));

        return companyMapper.toResponseDto(company);
    }

    @Override
    public Page<CompanyResponseDto> getAll(
            int page,
            int size,
            String sortBy,
            String direction) {

        companyLogger.info(
                "Fetching companies with pagination");

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(page, size, sort);

        return companyRepository
                .findAll(pageable)
                .map(companyMapper::toResponseDto);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        companyLogger.info(
                "Deleting company with id: " + id);

        Company company = companyRepository
                .findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found with id: "
                                        + id));

        companyRepository.delete(company);

        companyLogger.info(
                "Company deleted successfully");
    }
}