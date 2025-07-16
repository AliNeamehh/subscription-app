package com.example.subscription_service.dto;

import com.example.subscription_service.model.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class SubscribeRequestDto {


    @NotBlank(message = "tenantId is required")
    private String tenantId;

    @NotBlank(message = "planId is required")
    private String planId;
    @NotNull
    private Instant startDate;
    @NotNull
    private Instant endDate;
    @NotNull
    private SubscriptionStatus status;
    @NotNull
    private boolean isAutoRenew;

}
