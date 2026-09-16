package com.example.demo.agriculture.config;

import com.example.demo.agriculture.entity.AppUser;
import com.example.demo.agriculture.enums.FarmRole;
import com.example.demo.agriculture.repository.AppUserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer 
{

    @Bean
    CommandLineRunner initializeUsers(AppUserRepository repository,PasswordEncoder encoder) 
    {
     return args -> 
     {
      if (repository.findByUsername("admin").isEmpty()) 
      {
    	  AppUser admin = new AppUser();
    	  admin.setUsername("admin");
    	  admin.setPassword(encoder.encode("admin123"));
          admin.setRole(FarmRole.ADMIN);
          admin.setActive(true);
          repository.save(admin);
        }
        };
    }
}