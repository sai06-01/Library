package com.example.demo.agriculture.controller;

import com.example.demo.agriculture.dto.LoginRequestDto;
import com.example.demo.agriculture.dto.LoginResponseDto;
import com.example.demo.agriculture.entity.AppUser;
import com.example.demo.agriculture.enums.FarmRole;
import com.example.demo.agriculture.repository.AppUserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController 
{

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public AuthController(AppUserRepository repository,PasswordEncoder encoder)
    {

        this.repository = repository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) 
    {

        if (repository.findByUsername(user.getUsername())
        		.isPresent()) 
        {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        user.setPassword(encoder.encode(user.getPassword()));

        if (user.getRole() == null) 
        {
            user.setRole(FarmRole.FARMER);
        }

        user.setActive(true);

        AppUser savedUser =repository.save(user);
        savedUser.setPassword(null);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) 
    {

        AppUser user = repository .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!encoder.matches(
                request.getPassword(),
                user.getPassword())) 
        {

            throw new RuntimeException("Invalid username or password");
        }

        LoginResponseDto response = new LoginResponseDto();

        response.setMessage("Login successful");

        response.setUsername(user.getUsername());

        if (user.getRole() != null) 
        { response.setRole( user.getRole().name());
        }

        return ResponseEntity.ok(response);
    }
}