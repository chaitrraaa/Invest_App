package com.training.Building_app.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.Building_app.model.InvestmentAdvisor;
import com.training.Building_app.repository.InvestmentAdvisorRepository;

@Service
public class InvestmentAdvisorService {

    @Autowired
    private InvestmentAdvisorRepository investmentAdvisorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public InvestmentAdvisor createAdvisor(InvestmentAdvisor advisor) {
        advisor.setPassword(passwordEncoder.encode(advisor.getPassword())); // Hash password
        return investmentAdvisorRepository.save(advisor);
    }

    public List<InvestmentAdvisor> getAllAdvisors() {
        return investmentAdvisorRepository.findAll();
    }


    public String login(String email, String password) {
        System.out.println("Attempting to log in with email: " + email);
        InvestmentAdvisor advisor = investmentAdvisorRepository.findByEmail(email);
        if (advisor == null) {
            throw new RuntimeException("Investment Advisor not found with this email.");
        }
        
        System.out.println("Fetched advisor: " + advisor.getEmail());

        if (!passwordEncoder.matches(password, advisor.getPassword())) {
            throw new RuntimeException("Invalid password.");
        }

        return "Login successful!";
    }

    public String changePassword(String email, String newPassword) {
        InvestmentAdvisor advisor = investmentAdvisorRepository.findByEmail(email);
        if (advisor != null) {
            advisor.setPassword(passwordEncoder.encode(newPassword));
            investmentAdvisorRepository.save(advisor);
            return "Password changed successfully";
        }
        throw new RuntimeException("Advisor not found");
    }

    public String changeEmail(String currentEmail, String newEmail) {
        InvestmentAdvisor advisor = investmentAdvisorRepository.findByEmail(currentEmail);
        if (advisor != null) {
            advisor.setEmail(newEmail);
            investmentAdvisorRepository.save(advisor);
            return "Email changed successfully";
        }
        throw new RuntimeException("Advisor not found");
    }

    public String forgotPassword(String email) {
        InvestmentAdvisor advisor = investmentAdvisorRepository.findByEmail(email);
        if (advisor != null) {
            String newPassword = generateRandomPassword();
            advisor.setPassword(passwordEncoder.encode(newPassword));
            investmentAdvisorRepository.save(advisor);
            sendEmail(advisor.getEmail(), newPassword);
            return "A new password has been sent to your email.";
        }
        throw new RuntimeException("Email not found");
    }

    private void sendEmail(String to, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your new password");
        message.setText("Your new password is: " + password);
        mailSender.send(message);
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
