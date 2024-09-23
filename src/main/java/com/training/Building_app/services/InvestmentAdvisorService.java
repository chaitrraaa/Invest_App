package com.training.Building_app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.training.Building_app.model.InvestmentAdvisor;
import com.training.Building_app.repository.InvestmentAdvisorRepository;

@Service
public class InvestmentAdvisorService {

    private final InvestmentAdvisorRepository investmentAdvisorRepository;

    public InvestmentAdvisorService(InvestmentAdvisorRepository investmentAdvisorRepository) {
        this.investmentAdvisorRepository = investmentAdvisorRepository;
    }

    // Save a new advisor
    public InvestmentAdvisor createAdvisor(InvestmentAdvisor advisor) {
        return investmentAdvisorRepository.save(advisor);
    }

    // Fetch all advisors
    public List<InvestmentAdvisor> getAllAdvisors() {
        return investmentAdvisorRepository.findAll();
    }
}