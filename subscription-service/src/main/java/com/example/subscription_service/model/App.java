package com.example.subscription_service.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "apps")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class App extends Auditable {
    @Id
    private String id;

    @NotNull
    private String name;

    private String description;

    private List<String> planIds;

}
