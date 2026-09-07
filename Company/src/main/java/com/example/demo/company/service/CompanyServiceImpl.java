package com.example.demo.company.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.dto.LoginRequestDto;
import com.example.demo.company.dto.LoginResponseDto;

import com.example.demo.company.entity.Company;

import com.example.demo.company.exception.CompanyNotFoundException;
import com.example.demo.company.exception.InvalidLoginException;

import com.example.demo.company.logger.CompanyLogger;
import com.example.demo.company.mapper.CompanyMapper;
import com.example.demo.company.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService 
{

    private final CompanyRepository companyRepository;

    private final CompanyMapper companyMapper;

    private final CompanyLogger companyLogger;


    // CREATE
    @Override
    @Transactional(
            rollbackFor = Exception.class
    )
    public CompanyResponseDto createCompany(
            CompanyRequestDto dto) {

        companyLogger.info(
                "Creating company with email: "
                        + dto.getEmail());

        if (companyRepository.existsByEmail(
                dto.getEmail())) {

            companyLogger.warn(
                    "Company already exists with email: "
                            + dto.getEmail());

            throw new RuntimeException(
                    "Company already exists with this email");
        }

        Company company =
                companyMapper.toEntity(dto);

        Company savedCompany =
                companyRepository.save(company);

        companyLogger.info(
                "Company created successfully. ID: "
                        + savedCompany.getId());

        return companyMapper.toResponseDto(
                savedCompany);
    }


    // GET BY ID
    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDto getCompanyById(Long id) 
    {

        companyLogger.info(
                "Fetching company with id: " + id);

        Company company =
                companyRepository.findById(id)
                        .orElseThrow(() ->new CompanyNotFoundException( "Company not found with id: "+ id));

        return companyMapper.toResponseDto(company);
    }


    // GET ALL
    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDto>
    getAllCompanies() 
    {

        companyLogger.info("Fetching all companies");

        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    // PAGINATION + SORTING
    @Override
    @Transactional(readOnly = true)
    public Page<CompanyResponseDto> getCompanies(
            int page,
            int size,
            String sortBy,
            String sortDirection) {

        companyLogger.info(
                "Fetching companies - page: "
                        + page
                        + ", size: "
                        + size
                        + ", sortBy: "
                        + sortBy
                        + ", direction: "
                        + sortDirection);

        Sort sort;

        if (sortDirection.equalsIgnoreCase("desc")) 
        {

            sort = Sort.by(sortBy).descending();

        } 
        else 
        {

            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        sort
                );

        return companyRepository
                .findAll(pageable)
                .map(companyMapper::toResponseDto);
    }


    // UPDATE
    @Override
    @Transactional(
            rollbackFor = Exception.class
    )
    public CompanyResponseDto updateCompany(
            Long id,
            CompanyRequestDto dto) {

        companyLogger.info(
                "Updating company with id: "
                        + id
        );

        Company company =
                companyRepository.findById(id)
                        .orElseThrow(() ->
                                new CompanyNotFoundException(
                                        "Company not found with id: "
                                                + id));

        companyMapper.updateEntity(company,dto);

        Company updatedCompany =  companyRepository.save(company);

        companyLogger.info("Company updated successfully. ID: "+ id);

        return companyMapper.toResponseDto(updatedCompany);
    }


    // DELETE
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCompany(Long id) 
    {
    	companyLogger.info("Deleting company with id: "+ id);

        Company company =
                companyRepository.findById(id)
                        .orElseThrow(() ->new CompanyNotFoundException("Company not found with id: "+ id));

        companyRepository.delete(company);

        companyLogger.info("Company deleted successfully. ID: "+ id );
    }


    // LOGIN
    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(
            LoginRequestDto dto)
    {

        companyLogger.info("Login attempt for email: "+ dto.getEmail()
        );

        Company company = companyRepository
                        .findByEmail(dto.getEmail())
                        .orElseThrow(() ->
                                new InvalidLoginException(
                                        "Invalid email or password"));

        if (!company.getPassword()
                .equals(dto.getPassword())) {

            companyLogger.warn(
                    "Invalid password for email: "+ dto.getEmail());

            throw new InvalidLoginException("Invalid email or password" );
        }

        companyLogger.info("Login successful for email: "+ dto.getEmail());

        return LoginResponseDto.builder()
                .message("Login successful")
                .companyId(company.getId())
                .companyName(company.getName())
                .email(company.getEmail())
                .build();
    }
}