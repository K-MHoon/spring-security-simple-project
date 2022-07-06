package com.example.springsecuritysimpleproject.security.config;

import com.example.springsecuritysimpleproject.security.factory.MethodResourcesFactoryBean;
import com.example.springsecuritysimpleproject.service.resource.SecurityResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity
@RequiredArgsConstructor
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final SecurityResourceService securityResourceService;

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return mapBasedMethodSecurityMetadataSource();
    }

    @Bean
    public MapBasedMethodSecurityMetadataSource mapBasedMethodSecurityMetadataSource() {
        return new MapBasedMethodSecurityMetadataSource(methodResourcesMapFactoryBean().getObject());
    }

    @Bean
    public MethodResourcesFactoryBean methodResourcesMapFactoryBean() {
        MethodResourcesFactoryBean methodResourcesFactoryBean = new MethodResourcesFactoryBean();
        methodResourcesFactoryBean.setSecurityResourceService(securityResourceService);
        return methodResourcesFactoryBean;
    }
}
