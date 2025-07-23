package com.example.invoiceservice.model;

import com.example.invoiceservice.model.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
public class Transaction {

    private String id;
    private String invoiceId;
    private Instant paymentDate;
    private double amount;
    private PaymentMethod method;

    public Transaction() {

    }
}
