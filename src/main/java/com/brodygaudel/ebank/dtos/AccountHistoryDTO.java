/*
 * Copyright 2022-2023 the original author or authors.
 *
 * this file contains the dto AccountHistory
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.dtos;

import java.math.BigDecimal;
import java.util.List;

public record AccountHistoryDTO(
        String accountId,
        BigDecimal balance,
        int currentPage,
        int totalPages,
        int pageSize,
        List<AccountOperationDTO> accountOperationDTOS) {
}
