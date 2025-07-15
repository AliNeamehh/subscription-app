package com.example.subscription_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedBy
    private String modifiedBy;
    @CreatedBy
    private String createdBy;
    @LastModifiedDate
    private  Instant modifiedAt;

}
