package com.example.subscription_service.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan extends Auditable {

    @Id
    private String id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private double price;

    @NotNull
    private String billingCycle;
    @NotNull
    private int trialDays;

    private String basePlanId;

    private List<String> tagIds;



}
