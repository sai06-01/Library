package com.example.demo.agriculture.entity;

import com.example.demo.agriculture.enums.FarmRole;
import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
public class AppUser 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FarmRole role;

    @Column(nullable = false)
    private boolean active = true;

    public AppUser() {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public FarmRole getRole() 
    {
        return role;
    }

    public void setRole(FarmRole role) 
    {
        this.role = role;
    }

    public boolean isActive() 
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }
}