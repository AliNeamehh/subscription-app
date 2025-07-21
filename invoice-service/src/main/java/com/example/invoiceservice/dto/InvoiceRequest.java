package com.example.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class InvoiceRequest {

    private String tenantId;
    private String subscriptionId;
    private double amount;
    private Instant dueDate;


}
