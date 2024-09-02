package com.example.demo.walletapp.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Transactions")  //Realized Transaction is a MySQL keyword
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String transactionType; // FUND, WITHDRAW, etc.

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
}
