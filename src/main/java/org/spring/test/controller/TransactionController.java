package org.spring.test.controller;

import java.util.Set;
import java.util.UUID;
import org.spring.test.model.Transaction;
import org.spring.test.model.TransactionBody;
import org.spring.test.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping
    public ResponseEntity<Set<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Transaction> geTransactionById(@PathVariable UUID id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(ResponseEntity.status(HttpStatus.NOT_FOUND)::build);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Transaction> commitNewTransaction(@RequestBody TransactionBody transactionBody) {
        Transaction createdTransaction = transactionService.commitNewTransaction(transactionBody);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }
}
