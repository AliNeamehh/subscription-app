package com.example.invoiceservice.model;

import com.example.invoiceservice.model.enums.PaymentMethod;

import java.time.Instant;

public class Transaction {

    private String id;
    private String invoiceId;
    private Instant paymentDate;
    private double amount;
    private PaymentMethod method;

}
