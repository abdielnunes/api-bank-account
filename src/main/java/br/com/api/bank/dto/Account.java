package br.com.api.bank.dto;

import br.com.api.bank.enums.EnumEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

import java.util.List;

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
        System.out.print(ob.writeValueAsString(new Account(account, type, balance, event)));
    }

    public void deposit(final double amount) {
        balance = (balance + amount);
        System.out.print("Deposit made successfully: R$ "+ balance);
    }

    public void withdrawal(final double amount) {
        final boolean negative = Math.copySign(1, amount) < 0;// Não permitir subtrair valor negativo
        if(!negative) {
            System.out.println("Amount you want to withdraw is: R$ " + amount);
            if (balance >= amount) {
                balance = (balance - amount);
                System.out.println("Balance after withdrawal: R$ " + balance);
            } else {
                System.out.println("Your balance is less than R$ " + amount);
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