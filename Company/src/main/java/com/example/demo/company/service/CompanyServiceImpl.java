package com.example.demo.company.service;

import com.example.demo.company.dto.CompanyRequestDto;
import com.example.demo.company.dto.CompanyResponseDto;
import com.example.demo.company.entity.Company;
import com.example.demo.company.exception.CompanyAlreadyExistsException;
import com.example.demo.company.exception.CompanyNotFoundException;
import com.example.demo.company.mapper.CompanyMapper;
import com.example.demo.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            CompanyMapper companyMapper) {

        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }

    @Override
    public CompanyResponseDto createCompany(
            CompanyRequestDto request) {

        if (companyRepository.existsByEmail(request.getEmail())) {
            throw new CompanyAlreadyExistsException(
                    "Company with email already exists: "
                            + request.getEmail()
            );
        }

        Company company = companyMapper.toEntity(request);

        Company savedCompany =
                companyRepository.save(company);

        return companyMapper.toResponseDto(savedCompany);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyResponseDto> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDto getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() ->
                        new CompanyNotFoundException(
                                "Company not found with id: " + id
                        ));

        return companyMapper.toResponseDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyResponseDto getProfile(String name) {

        Company company =
                companyRepository.findByName(name)
                        .orElseThrow(() ->
                                new CompanyNotFoundException(
                                        "Company not found with name: "
                                                + name
                                ));

        return companyMapper.toResponseDto(company);
    }

    @Override
    public CompanyResponseDto updateCompany(
            Long id,
            CompanyRequestDto request) {

        Company existingCompany =
                companyRepository.findById(id)
                        .orElseThrow(() ->
                                new CompanyNotFoundException(
                                        "Company not found with id: "
                                                + id
                                ));

        /*
         * Version is NOT updated manually.
         * Hibernate @Version updates it automatically.
         */
        existingCompany.setName(request.getName());
        existingCompany.setEmail(request.getEmail());
        existingCompany.setPassword(request.getPassword());
        existingCompany.setRole(request.getRole());
        existingCompany.setStatus(request.getStatus());

        Company updatedCompany =
                companyRepository.save(existingCompany);

        return companyMapper.toResponseDto(updatedCompany);
    }

    @Override
    public void deleteCompany(Long id) {

        Company company =
                companyRepository.findById(id)
                        .orElseThrow(() ->
                                new CompanyNotFoundException(
                                        "Company not found with id: "
                                                + id
                                ));

        companyRepository.delete(company);
    }
}