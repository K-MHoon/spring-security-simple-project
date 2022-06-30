package com.example.springsecuritysimpleproject.config;

import com.example.springsecuritysimpleproject.repository.resources.ResourcesRepository;
import com.example.springsecuritysimpleproject.service.resource.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        return new SecurityResourceService(resourcesRepository);
    }
}
