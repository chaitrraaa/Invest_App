package com.training.Building_app.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for testing; enable in production
            .authorizeRequests()
                .requestMatchers("/api/admin/change-password").authenticated() // Require authentication for this endpoint
                .anyRequest().permitAll() // Allow other requests without authentication
            .and()
            .httpBasic(); // Use Basic Auth for simplicity; adjust as needed

        return http.build();
    }
}