/*
 * Copyright 2022-2023 the original author or authors.
 *
 * this file contains the dto SavingAccountCreationForm
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.dtos;

import java.math.BigDecimal;

public record SavingAccountCreationForm(BigDecimal initialBalance, double interestRate, Long customerId) {
}
