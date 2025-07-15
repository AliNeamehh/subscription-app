package com.example.subscription_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;


@Document(collection = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    private String id;

    @NotNull
    private String subscriptionId;
    @NotNull
    private double amount;

    @NotNull
    private List<Tax> taxes;
    @NotNull
    private String status;

    @CreatedDate
    private Instant createDate;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String modifiedBy;
    @LastModifiedDate
    private  Instant ModifiedAt;


}
