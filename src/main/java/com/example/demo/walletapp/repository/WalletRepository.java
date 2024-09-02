package com.example.demo.walletapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.walletapp.model.Wallet;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet,Long>{
    Optional<Wallet> findByEmail(String email);
}
