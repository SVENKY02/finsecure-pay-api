package com.finsecure.pay.controller;

import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TransactionController {

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "FinSecure Pay API");
        return response;
    }

    @PostMapping("/validate")
    public Map<String, Object> validateTransaction(@RequestBody Map<String, Object> transaction) {
        String amountStr = String.valueOf(transaction.getOrDefault("amount", "0.0"));
        double amount = Double.parseDouble(amountStr);
        String currency = (String) transaction.getOrDefault("currency", "USD");
        
        Map<String, Object> response = new HashMap<>();
        if (amount <= 0) {
            response.put("status", "REJECTED");
            response.put("reason", "Invalid amount");
            return response;
        }
        if (amount > 10000.00) {
            response.put("status", "FLAGGED_FOR_REVIEW");
            response.put("reason", "High value threshold exceeded");
            return response;
        }
        
        response.put("status", "APPROVED");
        response.put("currency", currency);
        response.put("amount", amount);
        return response;
    }
}
