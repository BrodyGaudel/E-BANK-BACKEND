/*
 * Copyright 2022-2023 the original author or authors.
 *
 * this file contains the dto customer
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.dtos;

import com.brodygaudel.ebank.enums.Sex;

import java.util.Date;

public record CustomerDTO(
        Long id,
        String firstname,
        String name,
        Date dateOfBirth,
        String placeOfBirth,
        String nationality,
        String cin,
        Sex sex) { }
