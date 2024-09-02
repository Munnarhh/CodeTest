package com.example.demo.walletapp.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.walletapp.model.FundRequest;
import com.example.demo.walletapp.model.Transaction;
import com.example.demo.walletapp.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Endpoint to fund a wallet from a bank account
    @PostMapping("/fund")
    public ResponseEntity<Transaction> fundWallet(@RequestBody FundRequest request) {
        Transaction transaction = transactionService.fundWallet(
                request.getWalletId(),
                request.getAccountNumber(),
                request.getPaymentGateway(),
                request.getAmount()
        );
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    // Endpoint to get transactions by wallet ID
    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<List<Transaction>> getWalletTransactions(@PathVariable Long walletId) {
        List<Transaction> transactions = transactionService.getWalletTransactions(walletId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
