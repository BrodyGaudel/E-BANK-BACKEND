/*
 * Copyright 2022-2023 the original author or authors.
 *
 * This file contains Security Configuration such as Security Filter Chain.
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String USER = "USER";

    @Bean
    public SecurityFilterChain filterChain(@NotNull HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(
                        c -> c.configurationSource(
                                request -> {
                                    CorsConfiguration config = new CorsConfiguration();

                                    config.setAllowedOrigins(Collections.singletonList(SecParams.HOST_CLIENT));
                                    config.setAllowedMethods(Collections.singletonList("*"));
                                    config.setAllowCredentials(true);
                                    config.setAllowedHeaders(Collections.singletonList("*"));
                                    config.setExposedHeaders(List.of(SecParams.AUTHORIZATION));
                                    config.setMaxAge(SecParams.MAX_AGE);
                                    return config;
                                }
                        ))
                .authorizeHttpRequests(
                        c -> c.requestMatchers(HttpMethod.GET, "/v1/customers/list").permitAll()
                                .requestMatchers(HttpMethod.GET, "/v1/customers/get/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/customers/search/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/customers/save/**").hasAuthority(ADMIN)
                                .requestMatchers(HttpMethod.PUT, "/v1/customers/update/**").hasAuthority(ADMIN)
                                .requestMatchers(HttpMethod.DELETE, "/v1/customers/delete/**").hasAuthority(ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/accounts/get/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/accounts/find/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/accounts/list/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/accounts/save/current/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/accounts/save/saving/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.PUT, "/v1/accounts/update/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/operations/credit/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/operations/debit/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.POST, "/v1/operations/transfer/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/operations/get/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/operations/{accountId}/pageOperations/**").hasAnyAuthority(USER, ADMIN)
                                .requestMatchers(HttpMethod.GET, "/v1/operations/generate/**").hasAnyAuthority(USER, ADMIN)
                                .anyRequest().authenticated());

        http.addFilterAfter(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }
}
