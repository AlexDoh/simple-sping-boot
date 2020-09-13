package org.spring.test.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

    private UUID id;
    private TransactionType type;
    private Double amount;
    private LocalDate effectiveDate;
}
