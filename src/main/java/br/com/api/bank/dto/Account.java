/*
 * Copyright 2022-2022 the original author or authors.
 *
 */
package br.com.api.bank.dto;

import br.com.api.bank.enums.EnumEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Class that represents data and attributes of a bank account.
 *
 * @since JDK 1.8
 * @since Spring Boot 2.6.2
 * @since Lombok 1.18.22
 * @author Abdiel Nunes
 * @version v0.0.1  d13 Jan 2022
 */
@Log4j2
@AllArgsConstructor
@Builder
public class Account {
    public String id;
    @JsonIgnore
    @Getter @Setter
    public String type;
    @Getter @Setter
    public double balance;
    @Getter @Setter
    @JsonIgnore
    private Integer event;//0 - destination 1- origin

    public Account() {
        balance = 0.00;
    }

    @SneakyThrows
    public void show(final String account) {
        ObjectMapper ob = new ObjectMapper();
        log.info("Json: {}", ob.writeValueAsString(new Account(account, type, balance, event)));
    }

    public void deposit(final double amount) {
        balance = (balance + amount);
        log.info("Deposit made successfully: R$ {}", balance);
    }

    public void withdrawal(final double amount) {
        final boolean negative = Math.copySign(1, amount) < 0;// Não permitir subtrair valor negativo
        if(!negative) {
            log.info("Amount you want to withdraw is: R$ {}", amount);
            if (balance >= amount) {
                balance = (balance - amount);
                log.info("Balance after withdrawal: R$ {}", balance);
            } else {
                log.info("Your balance is less than R$ {}", amount);
            }
        }
    }

    public void transfer(final double amount, final Account account) {
        withdrawal(amount);
        account.deposit(amount);
    }

    public Account search(final String acc, final List<Account> accounts) {
        return accounts.stream()
                .filter(account -> acc.equals(account.id))
                .findAny()
                .orElse(null);
    }

    public Account create(final double amount, Account c, final List<Account> accounts) {
        c = Account.builder()
                .id(c.id)
                .balance(amount)
                .type(EnumEntity.DEPOSIT.getValues())
                .event(c.getEvent())
                .build();

        accounts.add(c);

        return c;
    }

    @JsonIgnore
    public boolean isDestination(final int event) {
        return event == 0;
    }

    @JsonIgnore
    public boolean isOrigin(final int event) {
        return event == 1;
    }
}