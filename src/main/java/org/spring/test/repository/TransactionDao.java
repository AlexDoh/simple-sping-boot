package org.spring.test.repository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import lombok.Setter;
import org.spring.test.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
public class TransactionDao {

    private final Set<Transaction> transactionHistory = ConcurrentHashMap.newKeySet();
    private BigDecimal accountValue = new BigDecimal("0.0");

    public Transaction addToTransactionHistory(Transaction transaction) {
        transactionHistory.add(transaction);
        return transaction;
    }

    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionHistory.stream().filter(transaction -> transaction.getId().equals(id)).findAny();
    }
}
