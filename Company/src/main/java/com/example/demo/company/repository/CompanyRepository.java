package com.example.demo.company.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.company.entity.Company;

public interface CompanyRepository
        extends JpaRepository<Company, Long> {

    Optional<Company> findByEmail(String email);
}