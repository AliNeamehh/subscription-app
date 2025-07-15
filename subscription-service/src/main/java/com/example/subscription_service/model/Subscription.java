package com.example.subscription_service.model;

import com.example.subscription_service.model.enums.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "subscriptions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription extends Auditable {

    @Id
    private String id;
    @NotNull
    private String tenantId;

    @NotNull
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
