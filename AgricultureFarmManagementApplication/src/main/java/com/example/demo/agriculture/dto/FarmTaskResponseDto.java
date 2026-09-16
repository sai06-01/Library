package com.example.demo.agriculture.dto;

import com.example.demo.agriculture.enums.TaskStatus;

import java.time.LocalDate;

public class FarmTaskResponseDto 
{

    private Long id;
    private String taskName;
    private String description;
    private LocalDate taskDate;
    private TaskStatus status;
    private String assignedTo;
    private Long farmId;

    public Long getId() 
    {
        return id;
    }

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

    public void setId(Long id)
    {
        this.id = id;
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