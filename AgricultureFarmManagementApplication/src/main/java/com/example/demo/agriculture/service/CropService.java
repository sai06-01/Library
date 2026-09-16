package com.example.demo.agriculture.service;

import com.example.demo.agriculture.dto.CropRequestDto;
import com.example.demo.agriculture.dto.CropResponseDto;
import org.springframework.data.domain.Page;

public interface CropService 
{

    CropResponseDto createCrop(CropRequestDto request);

    Page<CropResponseDto> getAllCrops(
            int page,
            int size,
            String sortBy,
            String direction
    );

    CropResponseDto getCropById(Long id);

    CropResponseDto updateCrop(
            Long id,
            CropRequestDto request
    );

    void deleteCrop(Long id);
}