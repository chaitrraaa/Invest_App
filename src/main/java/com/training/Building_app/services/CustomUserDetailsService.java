package com.training.Building_app.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.Building_app.model.Admin;
import com.training.Building_app.repository.AdminRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public CustomUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the Admin user from the database by username
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        // Create and return a UserDetails object
        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .roles(admin.getRole()) // Ensure this is a string array
                .build();
    }
}