package com.example.demo.agriculture.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_records")
public class Audit 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;

    private Long entityId;

    private String action;

    private String performedBy;

    private LocalDateTime performedAt;

    public Audit() {
    }

    public Audit(
            String entityName,
            Long entityId,
            String action,
            String performedBy,
            LocalDateTime performedAt) 
    {

        this.entityName = entityName;
        this.entityId = entityId;
        this.action = action;
        this.performedBy = performedBy;
        this.performedAt = performedAt;
    }

    public Long getId()
    {
        return id;
    }

    public String getEntityName()
    {
        return entityName;
    }

    public Long getEntityId()
    {
        return entityId;
    }

    public String getAction()
    {
        return action;
    }

    public String getPerformedBy()
    {
        return performedBy;
    }

    public LocalDateTime getPerformedAt() 
    {
        return performedAt;
    }
}