package org.spring.test.service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.spring.test.model.Transaction;
import org.spring.test.model.TransactionBody;

public interface TransactionService {

    Set<Transaction> getAllTransactions();

    Optional<Transaction> getTransactionById(UUID id);

    Transaction commitNewTransaction(TransactionBody transactionBody);

    Double getCurrentAccountBalance();


}
