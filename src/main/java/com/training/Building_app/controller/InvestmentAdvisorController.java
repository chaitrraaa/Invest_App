package com.training.Building_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.Building_app.model.Basket;
import com.training.Building_app.model.InvestmentAdvisor;
import com.training.Building_app.services.BasketService;
import com.training.Building_app.services.InvestmentAdvisorService;

@RestController
@RequestMapping("/api/advisors")
public class InvestmentAdvisorController {

    @Autowired
    private InvestmentAdvisorService investmentAdvisorService;

    @Autowired
    private BasketService basketService;

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

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        try {
            String result = investmentAdvisorService.login(email, password);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // Log the error message and stack trace
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Change password endpoint
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String email, @RequestParam String newPassword) {
        return investmentAdvisorService.changePassword(email, newPassword);
    }

    // Change email endpoint
    @PostMapping("/change-email")
    public String changeEmail(@RequestParam String currentEmail, @RequestParam String newEmail) {
        return investmentAdvisorService.changeEmail(currentEmail, newEmail);
    }

    // Forgot password endpoint
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        return investmentAdvisorService.forgotPassword(email);
    }

    // Create basket endpoint
    @PostMapping("/create-basket")
    public Basket createBasket(@RequestBody Basket basket) {
        return basketService.createBasket(basket);
    }
}
