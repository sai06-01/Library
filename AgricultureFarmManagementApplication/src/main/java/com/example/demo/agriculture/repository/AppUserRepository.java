package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> 
{

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);
}