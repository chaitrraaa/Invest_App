package com.training.Building_app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.Building_app.model.InvestmentAdvisor;
import com.training.Building_app.services.InvestmentAdvisorService;

@RestController
@RequestMapping("/api/advisors")
public class InvestmentAdvisorController {

    private final InvestmentAdvisorService investmentAdvisorService;

    public InvestmentAdvisorController(InvestmentAdvisorService investmentAdvisorService) {
        this.investmentAdvisorService = investmentAdvisorService;
    }

    // Create a new Investment Advisor
    @PostMapping("/create")
    public InvestmentAdvisor createAdvisor(@RequestBody InvestmentAdvisor advisor) {
        return investmentAdvisorService.createAdvisor(advisor);
    }

    // Get all Investment Advisors
    @GetMapping("/all")
    public List<InvestmentAdvisor> getAllAdvisors() {
        return investmentAdvisorService.getAllAdvisors();
    }

    // Add more endpoints as needed
}