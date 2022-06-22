package com.example.springsecuritysimpleproject.security.config;

import com.example.springsecuritysimpleproject.security.filter.AjaxLoginProcessingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Order(0)
public class AjaxSecurityConfig {

    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain AjaxSecurityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(ajaxLoginProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().authenticated();
        http.csrf().disable();

        return http.build();
    }

    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager);
        return ajaxLoginProcessingFilter;
    }
}
