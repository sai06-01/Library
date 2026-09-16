package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.entity.*;
import com.example.demo.agriculture.exception.FarmNotFoundException;
import com.example.demo.agriculture.mapper.AgricultureMapper;
import com.example.demo.agriculture.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService 
{

    private final InventoryRepository repository;
    private final FarmRepository farmRepository;
    private final AgricultureMapper mapper;

    public InventoryServiceImpl(
            InventoryRepository repository,
            FarmRepository farmRepository,
            AgricultureMapper mapper) 
    {

        this.repository = repository;
        this.farmRepository = farmRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public InventoryResponseDto create(InventoryRequestDto dto)
    {

        Farm farm =
                farmRepository.findById(dto.getFarmId())
                        .orElseThrow(() ->
                                new FarmNotFoundException("Farm not found"));

        InventoryItem item =new InventoryItem();

        item.setItemName(dto.getItemName());
        item.setCategory(dto.getCategory());
        item.setQuantity(dto.getQuantity());
        item.setUnit(dto.getUnit());
        item.setReorderLevel(
                dto.getReorderLevel());
        item.setFarm(farm);

        return mapper.toInventoryDto(
                repository.save(item));
    }

    @Override
    public List<InventoryResponseDto>
    getByFarm(Long farmId) 
    {

        return repository
                .findByFarmId(farmId)
                .stream()
                .map(mapper::toInventoryDto)
                .toList();
    }

    @Override
    public List<InventoryResponseDto>
    getLowStock()
    {

        return repository
                .findAll()
                .stream()
                .filter(item ->
                        item.getReorderLevel() != null
                        && item.getQuantity()
                        <= item.getReorderLevel())
                .map(mapper::toInventoryDto)
                .toList();
    }

    @Override
    @Transactional
    public InventoryResponseDto update(Long id,InventoryRequestDto dto) 
    {

        InventoryItem item =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Inventory item not found"));

        Farm farm =farmRepository.findById(dto.getFarmId())
                        .orElseThrow(() ->
                                new FarmNotFoundException("Farm not found"));

        item.setItemName(dto.getItemName());
        item.setCategory(dto.getCategory());
        item.setQuantity(dto.getQuantity());
        item.setUnit(dto.getUnit());
        item.setReorderLevel(dto.getReorderLevel());
        item.setFarm(farm);

        return mapper.toInventoryDto(repository.save(item));
    }

    @Override
    @Transactional
    public void delete(Long id) 
    {

        repository.deleteById(id);
    }
}