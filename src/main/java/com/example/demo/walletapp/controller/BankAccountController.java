package com.example.demo.walletapp.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.walletapp.model.BankAccount;
import com.example.demo.walletapp.model.BankAccountRequest;
import com.example.demo.walletapp.service.BankAccountService;

import java.util.List;

@RestController
@RequestMapping("/api/bank-accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Endpoint to link a bank account to a wallet
    @PostMapping
    public ResponseEntity<BankAccount> linkBankAccount(@RequestBody BankAccountRequest request) {
        BankAccount bankAccount = bankAccountService.linkBankAccount(
                request.getWalletId(),
                request.getAccountNumber(),
                request.getAccountName(),
                request.getBank()
        );
        return new ResponseEntity<>(bankAccount, HttpStatus.CREATED);
    }

    // Endpoint to get all bank accounts linked to a wallet
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<BankAccount>> getBanksByWalletId(@PathVariable Long walletId) {
        List<BankAccount> bankAccounts = bankAccountService.getBanksByWalletId(walletId);
        if (bankAccounts.isEmpty()) {
            throw  new EntityNotFoundException();
        }
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }
}
