package com.example.EmployeeManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
    	
              return Optional.of("Admin"); 
    }
    
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImp();
    }
}
