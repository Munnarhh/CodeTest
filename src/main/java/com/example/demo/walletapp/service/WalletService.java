package com.example.demo.walletapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.walletapp.model.Wallet;
import com.example.demo.walletapp.repository.WalletRepository;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    // Create a new wallet with email and phone number
    public Wallet createWallet(String email, String phoneNumber) {
        // Check if a wallet with the same email already exists
        if (walletRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Wallet already exists with email: " + email);
        }

        Wallet wallet = new Wallet();
        wallet.setEmail(email);
        wallet.setPhoneNumber(phoneNumber);
        return walletRepository.save(wallet);
    }

    // Find wallet by email
    public Wallet getWalletByEmail(String email) {
        return walletRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with email: " + email));
    }

    // Find wallet by ID
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with ID: " + id));
    }


//    public Optional<Wallet> findById(Long id) {
//        return walletRepository.findById(id);
//    }
}

