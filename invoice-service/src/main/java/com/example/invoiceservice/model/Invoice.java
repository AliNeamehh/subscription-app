package com.example.invoiceservice.model;

import com.example.invoiceservice.model.enums.InvoiceStatus;

import java.time.Instant;

public class Invoice {

    private String id;
    private String tenantId;
    private String subscriptionId;
    private double amount;
    private Instant dueDate;
    private InvoiceStatus status;

}
