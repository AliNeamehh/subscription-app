package com.example.subscription_service.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    private String id;

    @NotNull
    private String invoiceId;
    @NotNull
    private String method;
    @NotNull
    private double amount;
    @NotNull
    private String status;
    @NotNull
    private int attemptCount;

    @CreatedDate
    private Instant createdAt;
}
