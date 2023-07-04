/*
 * Copyright 2022-2023 the original author or authors.
 *
 * This file contains web configurations
 *
 * You may use this file for commercial and/or educational purposes.
 * You can ask for a collaboration to improve this file.
 * You can modify it according to your needs.
 * The author does not promise any guarantees.
 */

package com.brodygaudel.ebank.util.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    private final Environment environment;

    public WebConfig(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        String origin = environment.getProperty("frontend");
        registry.addMapping("/**")
                .allowedOrigins(origin)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*");
    }
}
