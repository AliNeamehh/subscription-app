package com.example.subscription_service.dto;

import jakarta.validation.constraints.NotNull;
import jdk.jfr.Registered;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
@Data
public class PlanResponseDto {

    private String id;
    private String name;
    private String description;
    private double price;
    private String billingCycle;
    private int trialDays;
    private String basePlanId;
    private List<String> tagIds;
}
