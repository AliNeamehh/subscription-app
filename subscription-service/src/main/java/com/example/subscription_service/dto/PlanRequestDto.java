package com.example.subscription_service.dto;

import com.example.subscription_service.model.enums.BillingCycle;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
@Data
public class PlanRequestDto {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @NotNull
    private String description;

    @DecimalMin("0.0")
    private double price;


    private BillingCycle billingCycle;
    @NotNull
    private int trialDays;

    private String basePlanId;

    private List<String> tagIds;
}
