package com.training.Building_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.Building_app.services.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public String adminHome() {
        return "Welcome to the Admin API!";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword) {
        adminService.changePassword(newPassword);
        return "Password changed successfully";
    }
}

    // Add more endpoints for managing Investment Advisors
