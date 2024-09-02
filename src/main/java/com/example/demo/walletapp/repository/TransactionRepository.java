package com.example.demo.walletapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.walletapp.model.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByWalletId(Long walletId);
}
