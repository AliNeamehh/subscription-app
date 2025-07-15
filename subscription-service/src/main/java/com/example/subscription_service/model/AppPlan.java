package com.example.subscription_service.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "app_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppPlan {

    @Id
    private String id;

    @NotNull
    private String appId;
    @NotNull
    private String groupId;
}
