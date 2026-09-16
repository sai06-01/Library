package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.FarmTask;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FarmTaskRepository extends JpaRepository<FarmTask, Long> 
{

    List<FarmTask> findByFarmId(Long farmId);

    List<FarmTask> findByTaskDate(LocalDate date);
}