package com.example.demo.walletapp.model;

import lombok.Getter;
import lombok.Setter;

// Request class for linking a bank account
@Setter
@Getter
public class BankAccountRequest {
    private Long walletId;
    private String accountNumber;
    private String accountName;
    private String bank;
}
