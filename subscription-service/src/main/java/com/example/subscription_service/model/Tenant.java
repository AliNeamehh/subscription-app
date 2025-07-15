package com.example.subscription_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant extends Auditable {
    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Email
    private String email;

}
