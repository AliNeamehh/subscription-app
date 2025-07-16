package com.example.subscription_service.dto;

import com.example.subscription_service.model.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;
@Data
public class SubscribeResponseDto {

    private String id;
    private String tenantId;
    private String planId;
    private Instant startDate;
    private Instant endDate;
    private SubscriptionStatus status;
    private boolean isAutoRenew;
}
