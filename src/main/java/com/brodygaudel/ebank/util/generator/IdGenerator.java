/*
 * Copyright 2022-2023 the original author or authors.
 *
 * This file (service interface) contains the methode to generate bank account id..
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.util.generator;

public interface IdGenerator {
    /**
     * This method generates the (unique) identifier of the bank account.
     * the identifier of the bank accounts represents its bic code (or account details)
     *
     * @return unique id for a bank account
     */
    String autoGenerate();
}
