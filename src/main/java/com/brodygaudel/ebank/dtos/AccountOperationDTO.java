/*
 * Copyright 2022-2023 the original author or authors.
 *
 * this file contains the dto AccountOperation
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.dtos;

import com.brodygaudel.ebank.enums.OperationType;

import java.math.BigDecimal;
import java.util.Date;

public record AccountOperationDTO(
        Long id, Date operationDate,
        BigDecimal amount, OperationType type,
        String description) {
}
