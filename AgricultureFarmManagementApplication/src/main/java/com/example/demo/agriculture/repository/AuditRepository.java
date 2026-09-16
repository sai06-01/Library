package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.Audit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<Audit, Long> {
}