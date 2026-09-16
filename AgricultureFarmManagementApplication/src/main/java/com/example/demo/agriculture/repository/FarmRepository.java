package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.Farm;
import com.example.demo.agriculture.enums.FarmStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmRepository extends JpaRepository<Farm, Long> 
{

    boolean existsByFarmCode(String farmCode);

    Optional<Farm> findByFarmCode(String farmCode);

    List<Farm> findByStatus(FarmStatus status);

    List<Farm> findByLocationIgnoreCase(String location);
}