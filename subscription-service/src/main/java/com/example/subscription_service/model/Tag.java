package com.example.subscription_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "Tags")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends Auditable {
    @Id
    private String id;

    @NotNull
    private String name;

    private String description;

}
