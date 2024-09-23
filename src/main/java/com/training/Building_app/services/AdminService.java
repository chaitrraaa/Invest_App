package com.training.Building_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.Building_app.model.Admin;
import com.training.Building_app.repository.AdminRepository;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(String newPassword) {
        // Fetch the admin user (you might need to change this according to your needs)
        Admin admin = adminRepository.findByUsername("admin");
        if (admin != null) {
            String encodedPassword = passwordEncoder.encode(newPassword);
            admin.setPassword(encodedPassword);
            adminRepository.save(admin);
        } else {
            throw new RuntimeException("Admin user not found");
        }
    }
}
