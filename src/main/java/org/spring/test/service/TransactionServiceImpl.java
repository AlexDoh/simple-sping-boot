package org.spring.test.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.spring.test.exceptions.BadRequestException;
import org.spring.test.model.Transaction;
import org.spring.test.model.TransactionBody;
import org.spring.test.model.TransactionType;
import org.spring.test.repository.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactionDao;

    @Override
    public Set<Transaction> getAllTransactions() {
        return transactionDao.getTransactionHistory();
    }

    @Override
    public Optional<Transaction> getTransactionById(UUID id) {
        return transactionDao.getTransactionById(id);
    }

    @Override
    public Transaction commitNewTransaction(TransactionBody transactionBody) {
        Double amount = transactionBody.getAmount();
        TransactionType type = transactionBody.getType();
        switch (type) {
            case DEBIT:
                addToBalance(amount);
                break;
            case CREDIT:
                removeFromBalance(amount);
                break;
            default:

        }
        return addNewTransaction(transactionBody);
    }

    @Override
    public Double getCurrentAccountBalance() {
        return transactionDao.getAccountValue().doubleValue();
    }

    private void addToBalance(Double amount) {
        transactionDao.setAccountValue(
                transactionDao.getAccountValue().add(BigDecimal.valueOf(amount)));
    }

    private void removeFromBalance(Double amount) {
        if (isPossibleToRemoveFromBalance(amount)) {
            transactionDao.setAccountValue(
                    transactionDao.getAccountValue().subtract(BigDecimal.valueOf(amount)));
        } else {
            throw new BadRequestException("A transaction can't lead to the negative amount");
        }
    }

    private Transaction addNewTransaction(TransactionBody transactionBody) {
        Transaction newTransaction = Transaction.builder()
                .id(UUID.randomUUID())
                .amount(transactionBody.getAmount())
                .type(transactionBody.getType())
                .effectiveDate(LocalDate.now())
                .build();
        return transactionDao.addToTransactionHistory(newTransaction);
    }

    private boolean isPossibleToRemoveFromBalance(Double amount) {
        return amount <= transactionDao.getAccountValue().doubleValue();
    }
}
