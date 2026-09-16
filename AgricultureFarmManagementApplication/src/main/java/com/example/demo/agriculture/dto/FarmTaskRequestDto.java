package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.TaskStatus;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class FarmTaskRequestDto 
{

    @NotBlank
    private String taskName;

    private String description;

    @NotNull
    private LocalDate taskDate;

    @NotNull
    private TaskStatus status;

    @NotBlank
    private String assignedTo;

    @NotNull
    @Positive
    private Long farmId;

    public String getTaskName()
    {
        return taskName;
    }

    public String getDescription()
    {
        return description;
    }

    public LocalDate getTaskDate()
    {
        return taskDate;
    }

    public TaskStatus getStatus()
    {
        return status;
    }

    public String getAssignedTo()
    {
        return assignedTo;
    }

    public Long getFarmId() 
    {
        return farmId;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setTaskDate(LocalDate taskDate)
    {
        this.taskDate = taskDate;
    }

    public void setStatus(TaskStatus status)
    {
        this.status = status;
    }

    public void setAssignedTo(String assignedTo)
    {
        this.assignedTo = assignedTo;
    }

    public void setFarmId(Long farmId) 
    {
        this.farmId = farmId;
    }
}