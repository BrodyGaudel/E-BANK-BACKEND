/*
 * Copyright 2022-2023 the original author or authors.
 *
 * this file contains the entity Account's Operation
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.entities;

import com.brodygaudel.ebank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date operationDate;
    private String description;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private BankAccount bankAccount;

}
