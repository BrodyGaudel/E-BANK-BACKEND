/*
 * Copyright 2022-2023 the original author or authors.
 *
 * This file contains Security Parameters
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.security;

public final class SecParams {
    public static final String SECRET = "brody@spring.io";
    public static final String BEARER = "Bearer ";
    public static final String AUTHORIZATION = "Authorization";
    public static final String ROLES = "roles";
    public static final String HOST_CLIENT = "http://localhost:4200";
    public static final long MAX_AGE = 3600L;
    private SecParams(){}
}
