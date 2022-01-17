package br.com.api.bank.service;

import br.com.api.bank.dto.Account;
import br.com.api.bank.enums.EnumEntity;
import br.com.api.bank.request.RequestAccount;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class BankService {

    public Account getAccountById(Account acc, final String id, final List<Account> accounts) {
        return acc.search(id, accounts);
    }

    public Account event(final RequestAccount res, Account acc, final List<Account> accounts){

        //Verificar origin e destino de entrada (event)
        Account c = checkAccountEvent(res);

        acc = acc.search(c.id, accounts);

        if(nonNull(acc)) {
            switch (EnumEntity.get(res.getType())) {
                case DEPOSIT:
                    if (acc.isDestination(res.getEvent()))
                        acc.deposit(res.getAmount());
                    break;
            case WITHDRAWAL:
                if (acc.isOrigin(res.getEvent()))
                    acc.withdrawal(res.getAmount());
                break;
            case TRANSFER:
                Account destination = acc.search(res.getDestination(), accounts);
                if(isNull(destination))
                    destination = new Account();
                    c.id = res.getDestination();
                    c.setEvent(0);
                    destination.create(res.getAmount(), c, accounts);

                acc.transfer(res.getAmount(), destination);
                break;
            }
        } else {
            if (c.isDestination(c.getEvent())) {
                acc = new Account();
                acc = acc.create(res.getAmount(), c, accounts);
            }
        }
        return acc;
    }

    private Account checkAccountEvent(final RequestAccount r) {
        String selected = r.getDestination();
        int event = 0;

        if(nonNull(r.getOrigin())) {
            selected = r.getOrigin();
            event = 1;
        }
        return Account.builder()
                .id(selected)
                .event(event)
                .build();
    }
}