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
public class FarmTaskServiceImpl
        implements FarmTaskService 
        {

    private final FarmTaskRepository taskRepository;
    private final FarmRepository farmRepository;
    private final AgricultureMapper mapper;

    public FarmTaskServiceImpl(
            FarmTaskRepository taskRepository,
            FarmRepository farmRepository,
            AgricultureMapper mapper)
    {

        this.taskRepository = taskRepository;
        this.farmRepository = farmRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public FarmTaskResponseDto create(FarmTaskRequestDto dto) 
    {

        Farm farm =
                farmRepository.findById(dto.getFarmId())
                        .orElseThrow(() ->
                                new FarmNotFoundException( "Farm not found"));

        FarmTask task = new FarmTask();

        task.setTaskName(dto.getTaskName());
        task.setDescription(dto.getDescription());
        task.setTaskDate(dto.getTaskDate());
        task.setStatus(dto.getStatus());
        task.setAssignedTo(dto.getAssignedTo());
        task.setFarm(farm);

        return mapper.toTaskDto(
                taskRepository.save(task));
    }

    @Override
    public FarmTaskResponseDto get(Long id) 
    {

        return mapper.toTaskDto(
                taskRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found")));
    }

    @Override
    public List<FarmTaskResponseDto>
    getByFarm(Long farmId) 
    {

        return taskRepository
                .findByFarmId(farmId)
                .stream()
                .map(mapper::toTaskDto)
                .toList();
    }

    @Override
    @Transactional
    public FarmTaskResponseDto update(Long id,FarmTaskRequestDto dto)
    {

        FarmTask task =taskRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Task not found"));

        Farm farm =farmRepository.findById(dto.getFarmId())
                        .orElseThrow(() ->
                                new FarmNotFoundException("Farm not found"));

        task.setTaskName(dto.getTaskName());
        task.setDescription(dto.getDescription());
        task.setTaskDate(dto.getTaskDate());
        task.setStatus(dto.getStatus());
        task.setAssignedTo(dto.getAssignedTo());
        task.setFarm(farm);

        return mapper.toTaskDto(taskRepository.save(task));
    }

    @Override
    @Transactional
    public void delete(Long id)
    {

        taskRepository.deleteById(id);
    }
}