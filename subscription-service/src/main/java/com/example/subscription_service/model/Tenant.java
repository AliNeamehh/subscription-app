package com.example.subscription_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tenants")
public class Tenant {
    @Id
    private String id;
    private String name;
    private String email;
    private Instant createdAt;
}
