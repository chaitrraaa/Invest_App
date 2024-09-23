package com.training.Building_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.training.Building_app.model.Admin;
import com.training.Building_app.repository.AdminRepository;

@SpringBootApplication
public class InvestAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvestAppApplication.class, args);
    }

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); // Define PasswordEncoder bean }
	 */

    @Bean
    public CommandLineRunner commandLineRunner(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if the admin user already exists to avoid duplicates
            if (adminRepository.findByUsername("admin") == null) {
                String rawPassword = "admin123"; // The password you want to use
                String encodedPassword = passwordEncoder.encode(rawPassword); // Encode the password
                
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(encodedPassword); // Use the encoded password
                admin.setRole("ADMIN");

                adminRepository.save(admin); // Save the new admin
                System.out.println("Admin user created: " + admin.getUsername());
            } else {
                System.out.println("Admin user already exists.");
            }
        };
    }}