package org.spring.test.controller;

import org.spring.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/balance")
public class AccountController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping
    public ResponseEntity<Double> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getCurrentAccountBalance());
    }
}
