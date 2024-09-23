package com.training.Building_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.Building_app.model.InvestmentAdvisor;

@Repository
public interface InvestmentAdvisorRepository extends JpaRepository<InvestmentAdvisor, Long> {
    InvestmentAdvisor findByEmail(String email);
}