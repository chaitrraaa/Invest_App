package com.training.Building_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.Building_app.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByUsername(String username);
}