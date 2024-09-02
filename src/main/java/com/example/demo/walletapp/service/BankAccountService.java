package com.example.demo.walletapp.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.walletapp.model.BankAccount;
import com.example.demo.walletapp.model.Wallet;
import com.example.demo.walletapp.repository.BankAccountRepository;

import java.util.List;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final WalletService walletService;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, WalletService walletService) {
        this.bankAccountRepository = bankAccountRepository;
        this.walletService = walletService;
    }

    // Link a bank account to a wallet
    public BankAccount linkBankAccount(Long walletId, String accountNumber, String accountName, String bank) {
        Wallet wallet = walletService.getWalletById(walletId);

        // Check if the account number already exists for the given bank
        if (bankAccountRepository.findByAccountNumber(accountNumber).isPresent()) {
            throw new IllegalArgumentException("Bank account number already exists: " + accountNumber);
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setAccountName(accountName);
        bankAccount.setBank(bank);
        bankAccount.setWallet(wallet);

        return bankAccountRepository.save(bankAccount);
    }

    // Get all bank accounts linked to a wallet
    public List<BankAccount> getBanksByWalletId(Long walletId) {
        return bankAccountRepository.findByWalletId(walletId);
    }

    // Get a bank account by its account number
    public BankAccount getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new EntityNotFoundException("Bank account not found with account number: " + accountNumber));
    }
}
