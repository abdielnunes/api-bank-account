package br.com.api.bank.controller;

import br.com.api.bank.dto.Account;
import br.com.api.bank.enums.EnumEntity;
import br.com.api.bank.request.RequestAccount;
import br.com.api.bank.response.ResponseTransfer;
import br.com.api.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RestController
public class AccountController {

    @Autowired
    BankService service;

    Account acc = new Account();
    List<Account> accounts = new ArrayList<>();

    @PostMapping("/reset")
    public ResponseEntity reset() {
        acc = new Account();
        accounts = new ArrayList<>();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<Double> getById(@RequestParam final String account_id) {
        final Account c = service.getAccountById(acc, account_id, accounts);
        if(nonNull(c)) {
            return new ResponseEntity<>(c.getBalance(), HttpStatus.OK);
        }
        return new ResponseEntity<>(0D, HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/event", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> event(@RequestBody RequestAccount res) {
        final Account c = service.event(res, acc, accounts);

        if(nonNull(c)) {
            Account destination = null;
            if(EnumEntity.isDeposit(res.getType())
                    || EnumEntity.isTransfer(res.getType())) {
                destination = c.search(res.getDestination(), accounts);
            }

            Account origin = null;
            if(EnumEntity.isTransfer(res.getType())
                    || EnumEntity.isWithdrawal(res.getType())) {
                origin = c.search(res.getOrigin(), accounts);
            }

            return new ResponseEntity<>(ResponseTransfer.builder()
                    .origin(origin)
                    .destination(destination)
                    .build(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(0D, HttpStatus.NOT_FOUND);
    }
}