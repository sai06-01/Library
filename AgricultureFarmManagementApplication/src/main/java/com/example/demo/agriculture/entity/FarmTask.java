package com.example.demo.agriculture.entity;

import com.example.demo.agriculture.enums.TaskStatus;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "farm_tasks")
public class FarmTask 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String taskName;

    private String description;

    @Column(nullable = false)
    private LocalDate taskDate;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private String assignedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    public FarmTask() {
    }

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

    public Farm getFarm() 
    {
        return farm;
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

    public void setFarm(Farm farm)
    {
        this.farm = farm;
    }
}