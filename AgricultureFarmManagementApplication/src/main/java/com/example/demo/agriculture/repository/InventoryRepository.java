package com.example.demo.agriculture.repository;

import com.example.demo.agriculture.entity.InventoryItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<InventoryItem, Long>
{

    List<InventoryItem> findByFarmId(Long farmId);

    List<InventoryItem>
    findByQuantityLessThanEqual(
            Double quantity);
}