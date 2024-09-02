package com.example.demo.walletapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.walletapp.model.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    List<BankAccount> findByWalletId(Long walletId);
    Optional<BankAccount> findByAccountNumber(String accountNumber);
}
