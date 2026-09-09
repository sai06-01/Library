package com.example.demo.company.dto;

public class LoginResponseDto {

    private String message;
    private Long companyId;
    private String name;
    private String email;
    private String role;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String message,
                            Long companyId,
                            String name,
                            String email,
                            String role) {

        this.message = message;
        this.companyId = companyId;
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}