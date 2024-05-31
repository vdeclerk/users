package com.v15k.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain authenticationSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("authenticationSecurityFilterChain");
        http
            .cors(Customizer.withDefaults())
            .securityMatcher("/user")
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
