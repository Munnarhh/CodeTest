package com.example.demo.walletapp.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.walletapp.model.Wallet;
import com.example.demo.walletapp.model.WalletRequest;
import com.example.demo.walletapp.service.WalletService;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // Endpoint to create a new wallet
    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody WalletRequest walletRequest) {
        Wallet wallet = walletService.createWallet(walletRequest.getEmail(), walletRequest.getPhoneNumber());
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    // Endpoint to get wallet by email
    @GetMapping("/{email}")
    public ResponseEntity<Wallet> getWalletByEmail(@PathVariable String email) {
        Wallet wallet = walletService.getWalletByEmail(email);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
}
