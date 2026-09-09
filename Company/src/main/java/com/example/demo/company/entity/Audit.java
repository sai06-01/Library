package com.example.demo.company.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "company_audit")
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String action;

    private Long companyId;

    private String performedBy;

    private LocalDateTime performedAt;

    private String details;

    public Audit() {
    }

    public Audit(String action,
                 Long companyId,
                 String performedBy,
                 LocalDateTime performedAt,
                 String details) {

        this.action = action;
        this.companyId = companyId;
        this.performedBy = performedBy;
        this.performedAt = performedAt;
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public LocalDateTime getPerformedAt() {
        return performedAt;
    }

    public void setPerformedAt(LocalDateTime performedAt) {
        this.performedAt = performedAt;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}