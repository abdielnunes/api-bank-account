/*
 * Copyright 2022-2022 the original author or authors.
 *
 */
package br.com.api.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Class for application context configuration.
 *
 * @since JDK 1.8
 * @since Spring Boot 2.6.2
 * @author Abdiel Nunes
 * @version v0.0.1  d13 Jan 2022
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}