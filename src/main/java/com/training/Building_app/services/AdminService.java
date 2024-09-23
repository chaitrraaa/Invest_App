package com.training.Building_app.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.Building_app.model.Admin;
import com.training.Building_app.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(String newPassword) {
        // Assuming a logged-in Admin user is fetched from security context
        Admin admin = adminRepository.findByUsername("currentLoggedInUsername"); // adjust as needed
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
    }

    // Additional methods for managing Investment Advisors
}
