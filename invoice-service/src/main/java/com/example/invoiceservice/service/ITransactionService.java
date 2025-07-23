package com.example.invoiceservice.service;

import com.example.invoiceservice.dto.PaymentRequest;

public interface ITransactionService {

    public void processPayment(PaymentRequest paymentRequest);
}
