package com.example.subscription_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TagRequestDto {

    @NotBlank(message = "The Name is required")
    private String name;

    @NotBlank(message = "The description is Required")
    private String description;

}
