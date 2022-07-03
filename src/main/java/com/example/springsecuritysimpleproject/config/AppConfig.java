package com.example.springsecuritysimpleproject.config;

import com.example.springsecuritysimpleproject.repository.resources.ResourcesRepository;
import com.example.springsecuritysimpleproject.repository.security.AccessIpRepository;
import com.example.springsecuritysimpleproject.service.resource.SecurityResourceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository,
                                                           AccessIpRepository accessIpRepository) {
        return new SecurityResourceService(resourcesRepository, accessIpRepository);
    }
}
