package com.example.subscription_service.model;

import com.example.subscription_service.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;


@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends Auditable {

    @Id
    private String id;

    @NotNull
    private String tenantId;

    @NotNull
    @Email
    private String email;


    @NotNull
    private String name;

    @NotNull
    private UserRole role;

}
