package com.example.demo.agriculture.config;

import com.example.demo.agriculture.entity.AppUser;
import com.example.demo.agriculture.repository.AppUserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig 
{

    private final AppUserRepository repository;

    public SecurityConfig(AppUserRepository repository)
    {
        this.repository = repository;
    }

    // SECURITY FILTER CHAIN

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception
    {

        http
                // Disable CSRF for REST APIs
                .csrf(csrf -> csrf.disable())

                // Allow H2 console to display correctly
                .headers(headers ->headers.frameOptions(frame ->frame.sameOrigin()))

                // Use stateless security for REST APIs
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // AUTHORIZATION

                .authorizeHttpRequests(auth -> auth
                        // Authentication
                        .requestMatchers(
                                "/api/auth/**"
                        ).permitAll()

                        // Swagger / OpenAPI
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // H2 Console
                        .requestMatchers( "/h2-console/**").permitAll()

                        // Actuator
                        .requestMatchers( "/actuator/**").permitAll()

                        // Farm APIs
                        .requestMatchers( "/api/farms/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )

                        // Crop APIs
                        .requestMatchers(
                        "/api/crops/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )
                        // Farm Task APIs
                        .requestMatchers( "/api/tasks/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )

                        // Inventory APIs
                        .requestMatchers("/api/inventory/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )

                        // Transaction APIs
                        .requestMatchers("/api/transactions/**"
                        ).hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )

                        // File APIs
                        .requestMatchers(
                                "/api/files/**").hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER"
                        )

                        // Profile APIs
                       
                        .requestMatchers("/api/profile/**")
                        .hasAnyRole(
                                "ADMIN",
                                "FARMER",
                                "MANAGER")

                        // Everything else requires login
                        .anyRequest().authenticated()
                )

                // Basic Authentication
                .httpBasic(httpBasic -> {});

        return http.build();
    }
    // USER DETAILS SERVICE

    @Bean
    public UserDetailsService userDetailsService() 
    {

        return username ->
        {

            AppUser user = repository.findByUsername(username)
            		.orElseThrow(() ->new UsernameNotFoundException("User not found"));

            UserDetails userDetails =User.builder().username(user.getUsername())
                            .password(user.getPassword())
                            .roles(user.getRole().name())
                            .disabled(!user.isActive())
                            .build();

            return userDetails;
        };
    }
    // PASSWORD ENCODER

    @Bean
    public PasswordEncoder passwordEncoder() 
    {

        return new BCryptPasswordEncoder();
    }
}