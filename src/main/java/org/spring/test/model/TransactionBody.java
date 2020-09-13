package org.spring.test.model;

import lombok.Data;

@Data
public class TransactionBody {

    private TransactionType type;
    private Double amount;
}
