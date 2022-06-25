package com.example.springsecuritysimpleproject.security.config;

import com.example.springsecuritysimpleproject.security.common.AjaxAccessDeniedHandler;
import com.example.springsecuritysimpleproject.security.common.AjaxLoginAuthenticationEntryPoint;
import com.example.springsecuritysimpleproject.security.filter.AjaxLoginProcessingFilter;
import com.example.springsecuritysimpleproject.security.handler.AjaxAuthenticationFailureHandler;
import com.example.springsecuritysimpleproject.security.handler.AjaxAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@Order(0)
public class AjaxSecurityConfig {

    private final AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain AjaxSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .antMatcher("/api/**")
                .authorizeRequests()
                .antMatchers("/api/messages").hasRole("MANAGER")
                .anyRequest().authenticated();
        http.csrf().disable();
        http.exceptionHandling()
                .authenticationEntryPoint(new AjaxLoginAuthenticationEntryPoint())
                .accessDeniedHandler(ajaxAccessDeniedHandler());

        customConfigureAjax(http);

        return http.build();
    }

    private void customConfigureAjax(HttpSecurity http) throws Exception {
        http
                .apply(new AjaxLoginConfigurer<>())
                .successHandlerAjax(ajaxAuthenticationSuccessHandler())
                .failureHandlerAjax(ajaxAuthenticationFailureHandler())
                .setAuthenticationManager(authenticationManager)
                .loginProcessingUrl("/api/login");
    }

    @Bean
    public AccessDeniedHandler ajaxAccessDeniedHandler() {
        return new AjaxAccessDeniedHandler();
    }

    /**
     * DSL로 대체되어 사용되지 않는다.
     * @return
     */
//    @Bean
    public AjaxLoginProcessingFilter ajaxLoginProcessingFilter() {
        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = new AjaxLoginProcessingFilter();
        ajaxLoginProcessingFilter.setAuthenticationManager(authenticationManager);
        ajaxLoginProcessingFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler());
        ajaxLoginProcessingFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler());
        return ajaxLoginProcessingFilter;
    }

    @Bean("ajaxAuthenticationSuccessHandler")
    public AuthenticationSuccessHandler ajaxAuthenticationSuccessHandler() {
        return new AjaxAuthenticationSuccessHandler();
    }

    @Bean("ajaxAuthenticationFailureHandler")
    public AuthenticationFailureHandler ajaxAuthenticationFailureHandler() {
        return new AjaxAuthenticationFailureHandler();
    }
}
