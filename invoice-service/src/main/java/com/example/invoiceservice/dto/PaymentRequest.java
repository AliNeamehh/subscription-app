package com.example.invoiceservice.dto;


import com.example.invoiceservice.model.enums.PaymentMethod;
import lombok.Data;

@Data
public class PaymentRequest {
    private String invoiceId;
    private double amount;
    private PaymentMethod paymentMethod;
}
