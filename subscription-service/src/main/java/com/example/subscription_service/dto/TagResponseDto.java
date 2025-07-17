package com.example.subscription_service.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TagResponseDto {
    private String id;
    private String name;
    private String description;

}
