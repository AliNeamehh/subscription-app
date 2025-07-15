package com.example.subscription_service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "taxes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {
    @Id
    private String id;
    private String name;
    private double rate;
    private double amount;
}
