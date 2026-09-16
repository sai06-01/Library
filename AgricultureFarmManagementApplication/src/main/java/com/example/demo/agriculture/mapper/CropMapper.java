package com.example.demo.agriculture.mapper;

import com.example.demo.agriculture.dto.CropRequestDto;
import com.example.demo.agriculture.dto.CropResponseDto;
import com.example.demo.agriculture.entity.Crop;
import com.example.demo.agriculture.entity.Farm;
import org.springframework.stereotype.Component;

@Component
public class CropMapper 
{

    public Crop toEntity(CropRequestDto request) 
    {

        Crop crop = new Crop();

        crop.setCropName(request.getCropName());
        crop.setCropType(request.getCropType());
        crop.setPlantingDate(request.getPlantingDate());
        crop.setExpectedHarvestDate(
                request.getExpectedHarvestDate()
        );
        crop.setExpectedYield(
                request.getExpectedYield()
        );
        crop.setStatus(request.getStatus());

        return crop;
    }

    public CropResponseDto toResponseDto(Crop crop) 
    {

        CropResponseDto response = new CropResponseDto();

        response.setId(crop.getId());
        response.setCropName(crop.getCropName());
        response.setCropType(crop.getCropType());
        response.setPlantingDate(crop.getPlantingDate());
        response.setExpectedHarvestDate(
                crop.getExpectedHarvestDate()
        );
        response.setExpectedYield(
                crop.getExpectedYield()
        );
        response.setStatus(crop.getStatus());

        if (crop.getFarm() != null) 
        {
            response.setFarmId(
                    crop.getFarm().getId()
            );
        }

        return response;
    }

    public void updateEntity(
            Crop crop,
            CropRequestDto request)
    {

        crop.setCropName(request.getCropName());
        crop.setCropType(request.getCropType());
        crop.setPlantingDate(request.getPlantingDate());
        crop.setExpectedHarvestDate(
                request.getExpectedHarvestDate()
        );
        crop.setExpectedYield(
                request.getExpectedYield()
        );
        crop.setStatus(request.getStatus());
    }
}