package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.CropRequestDto;
import com.example.demo.agriculture.dto.CropResponseDto;
import com.example.demo.agriculture.entity.Crop;
import com.example.demo.agriculture.entity.Farm;
import com.example.demo.agriculture.exception.CropNotFoundException;
import com.example.demo.agriculture.mapper.CropMapper;
import com.example.demo.agriculture.repository.CropRepository;
import com.example.demo.agriculture.repository.FarmRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CropServiceImpl implements CropService 
{

    private final CropRepository cropRepository;
    private final CropMapper cropMapper;
    private final FarmRepository farmRepository;

    public CropServiceImpl(
            CropRepository cropRepository,
            CropMapper cropMapper,
            FarmRepository farmRepository) 
    {

        this.cropRepository = cropRepository;
        this.cropMapper = cropMapper;
        this.farmRepository = farmRepository;
    }

    @Override
    public CropResponseDto createCrop(
            CropRequestDto request) 
    {

        Crop crop = cropMapper.toEntity(request);

        Farm farm = farmRepository
                .findById(request.getFarmId())
                .orElseThrow(() ->new RuntimeException("Farm not found with id: "+ request.getFarmId()));

        crop.setFarm(farm);

        Crop savedCrop =cropRepository.save(crop);

        return cropMapper.toResponseDto(savedCrop);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CropResponseDto> getAllCrops(
            int page,
            int size,
            String sortBy,
            String direction)
    {

        Sort sort;

        if ("desc".equalsIgnoreCase(direction)) 
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
                        sort );

        return cropRepository
                .findAll(pageable)
                .map(cropMapper::toResponseDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CropResponseDto getCropById(Long id)
    {

        Crop crop = cropRepository
                .findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException(
                                "Crop not found with id: " + id));

        return cropMapper.toResponseDto(crop);
    }

    @Override
    public CropResponseDto updateCrop(
            Long id,
            CropRequestDto request) 
    {

        Crop existingCrop = cropRepository.findById(id)
                .orElseThrow(() ->new CropNotFoundException("Crop not found with id: " + id));

        cropMapper.updateEntity(
                existingCrop,
                request);

        Farm farm = farmRepository
                .findById(request.getFarmId())
                .orElseThrow(() ->
                        new RuntimeException("Farm not found with id: "+ request.getFarmId()));

        existingCrop.setFarm(farm);

        Crop updatedCrop =cropRepository.save(existingCrop);

        return cropMapper.toResponseDto(updatedCrop);
    }

    @Override
    public void deleteCrop(Long id) 
    {

        Crop crop = cropRepository
                .findById(id)
                .orElseThrow(() ->
                        new CropNotFoundException("Crop not found with id: " + id));

        cropRepository.delete(crop);
    }
}