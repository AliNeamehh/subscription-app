package com.example.subscription_service.controller;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "Plan Management", description = "APIs for managing plans")
public class PlanController {

    private final PlanService planService;


    @PostMapping
    @Operation(summary = "Create a new plan")
    public ResponseEntity<PlanResponseDto> createPatient(@Validated
                                                             @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto createdPlan = planService.createPlan(planRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }


}
