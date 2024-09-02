package com.example.demo.walletapp.model;

import lombok.Getter;
import lombok.Setter;

// Request class for funding a wallet
@Setter
@Getter
public class FundRequest {
    private Long walletId;
    private String accountNumber;
    private String paymentGateway;
    private Double amount;
}
