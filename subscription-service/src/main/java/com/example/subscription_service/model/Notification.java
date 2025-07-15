package com.example.subscription_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    private String id;
    @NotNull
    private String tenantId;
    @NotNull
    private String type;
    @NotNull
    private String message;
    @NotNull
    private boolean readStatus;

    @CreatedDate
    private Instant createdAt;

}
