package com.training.Building_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for testing; enable it in production
            .authorizeRequests()
                // Admin endpoints
                .requestMatchers("/api/admin/**").hasRole("ADMIN") // Only users with ADMIN role can access
                // Investment Advisor endpoints
                .requestMatchers("/api/advisors/all").hasAnyRole("INVESTMENT_ADVISOR", "ADMIN") // Allow access to both roles
// Only users with INVESTMENT_ADVISOR role can access
                // Investor endpoints
                .requestMatchers("/api/investors/**").hasRole("INVESTOR") // Only users with INVESTOR role can access
                .anyRequest().permitAll() // Allow all other requests (e.g., public access pages)
            .and()
            .httpBasic() // Use Basic Auth; for production, consider more secure methods like JWT
            .and()
            .formLogin() // Enable form-based authentication for easy login UI
                .loginPage("/login") // Custom login page (optional)
                .permitAll()
            .and()
            .logout() // Enable logout functionality
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Define PasswordEncoder bean for password hashing
    }
}