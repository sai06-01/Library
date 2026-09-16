package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.*;
import com.example.demo.agriculture.entity.Audit;
import com.example.demo.agriculture.entity.Farm;
import com.example.demo.agriculture.enums.FarmStatus;
import com.example.demo.agriculture.exception.*;
import com.example.demo.agriculture.logger.AgricultureLogger;
import com.example.demo.agriculture.mapper.AgricultureMapper;
import com.example.demo.agriculture.repository.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FarmServiceImpl implements FarmService 
{

    private final FarmRepository farmRepository;
    private final AuditRepository auditRepository;
    private final AgricultureMapper mapper;
    private final AgricultureLogger logger;

    public FarmServiceImpl(
            FarmRepository farmRepository,
            AuditRepository auditRepository,
            AgricultureMapper mapper,
            AgricultureLogger logger)
    {

        this.farmRepository = farmRepository;
        this.auditRepository = auditRepository;
        this.mapper = mapper;
        this.logger = logger;
    }

    @Override
    @Transactional
    public FarmResponseDto createFarm(FarmRequestDto dto) 
    {

        if (farmRepository.existsByFarmCode(dto.getFarmCode())) 
        {

            throw new FarmAlreadyExistsException(
                    "Farm code already exists: "
                            + dto.getFarmCode());
        }

        Farm farm = new Farm();

        farm.setFarmCode(dto.getFarmCode());
        farm.setFarmName(dto.getFarmName());
        farm.setOwnerName(dto.getOwnerName());
        farm.setLandArea(dto.getLandArea());
        farm.setPrimaryCrop(dto.getPrimaryCrop());
        farm.setStatus(dto.getStatus());
        farm.setLocation(dto.getLocation());
        farm.setDescription(dto.getDescription());

        Farm saved =farmRepository.save(farm);

        saveAudit(saved.getId(),"CREATE");

        logger.info("Farm created: "+ saved.getFarmCode());

        return mapper.toFarmDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FarmResponseDto getFarm(Long id)
    {

        return mapper.toFarmDto(findFarm(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FarmResponseDto> getAllFarms(Pageable pageable) 
    {

        return farmRepository
                .findAll(pageable)
                .map(mapper::toFarmDto);
    }

    @Override
    @Transactional
    public FarmResponseDto updateFarm(
            Long id,
            FarmRequestDto dto) 
    {

        Farm farm = findFarm(id);

        if (!farm.getFarmCode()
                .equals(dto.getFarmCode())
                && farmRepository.existsByFarmCode(
                        dto.getFarmCode()))
        {

            throw new FarmAlreadyExistsException("Farm code already exists: "+ dto.getFarmCode());
        }

        farm.setFarmCode(dto.getFarmCode());
        farm.setFarmName(dto.getFarmName());
        farm.setOwnerName(dto.getOwnerName());
        farm.setLandArea(dto.getLandArea());
        farm.setPrimaryCrop(dto.getPrimaryCrop());
        farm.setStatus(dto.getStatus());
        farm.setLocation(dto.getLocation());
        farm.setDescription(dto.getDescription());

        Farm updated =farmRepository.save(farm);

        saveAudit(updated.getId(),"UPDATE");

        logger.info("Farm updated: "+ updated.getFarmCode());

        return mapper.toFarmDto(updated);
    }

    @Override
    @Transactional
    public void deleteFarm(Long id)
    {

        Farm farm = findFarm(id);

        farmRepository.delete(farm);

        saveAudit(id,"DELETE");

        logger.info("Farm deleted: " + id);
    }

    @Override
    public List<FarmResponseDto>
    getFarmsByStatus(FarmStatus status)
    {

        return farmRepository
                .findByStatus(status)
                .stream()
                .map(mapper::toFarmDto)
                .toList();
    }

    @Override
    public List<FarmResponseDto>
    getFarmsByLocation(String location)
    {

        return farmRepository
                .findByLocationIgnoreCase(location)
                .stream()
                .map(mapper::toFarmDto)
                .toList();
    }

    @Override
    public long countFarms()
    {

        return farmRepository.count();
    }

    @Override
    public void generateReport() {

        logger.info("Scheduled agriculture report. "+ "Total farms = "+ farmRepository.count());
    }

    private Farm findFarm(Long id) 
    {

        return farmRepository
                .findById(id)
                .orElseThrow(() ->
                        new FarmNotFoundException("Farm not found with id: "+ id));
    }

    private void saveAudit(
            Long id,
            String action) {

        Audit audit =new Audit("Farm",
                        id,
                        action,
                        "SYSTEM",
                        LocalDateTime.now());

        auditRepository.save(audit);
    }
}