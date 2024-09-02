package com.example.demo.walletapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.walletapp.model.BankAccount;
import com.example.demo.walletapp.model.Transaction;
import com.example.demo.walletapp.model.Wallet;
import com.example.demo.walletapp.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final BankAccountService bankAccountService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              WalletService walletService,
                              BankAccountService bankAccountService) {
        this.transactionRepository = transactionRepository;
        this.walletService = walletService;
        this.bankAccountService = bankAccountService;
    }

    // Fund a wallet from a bank account
    public Transaction fundWallet(Long walletId, String accountNumber, String paymentGateway, Double amount) {
        // Log payment using the generic method
        logPayment(paymentGateway, accountNumber, amount);

        // Retrieve the Wallet and BankAccount
        Wallet wallet = walletService.getWalletById(walletId);
        BankAccount bankAccount = bankAccountService.getBankAccountByAccountNumber(accountNumber);

        // Create a transaction
        Transaction transaction = new Transaction();
        transaction.setTransactionType("FUND");
        transaction.setAmount(amount);
        transaction.setWallet(wallet);
        return transactionRepository.save(transaction);
    }

    // General method to simulate payment via various gateways
    private void logPayment(String paymentGateway, String accountNumber, Double amount) {
        switch (paymentGateway.toUpperCase()) {
            case "FLUTTERWAVE":
                logger.info("Entering Flutterwave case");
                logger.info("Simulating payment via Flutterwave for account: {}", accountNumber);
                logger.info("Amount: {} successfully debited via Flutterwave.", amount);
                break;
            case "PAYSTACK":
                System.out.println("Simulating payment via Paystack for account: " + accountNumber);
                System.out.println("Amount: " + amount + " successfully debited via Paystack.");
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment gateway: " + paymentGateway);
        }
    }

    // Get all transactions by wallet ID
    public List<Transaction> getWalletTransactions(Long walletId) {
        return transactionRepository.findByWalletId(walletId);
    }
}
