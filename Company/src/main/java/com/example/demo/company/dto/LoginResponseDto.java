package com.example.demo.company.dto;

public class LoginResponseDto {

    private boolean success;

    private String message;

    private Long companyId;

    private String name;

    public LoginResponseDto() {
    }

    public LoginResponseDto(
            boolean success,
            String message,
            Long companyId,
            String name) {

        this.success = success;
        this.message = message;
        this.companyId = companyId;
        this.name = name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}