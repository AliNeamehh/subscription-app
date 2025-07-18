package com.example.subscription_service.controller;
import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.service.IPlanService;
import com.example.subscription_service.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
@Tag(name = "Plan Management", description = "APIs for managing plans")
public class PlanController {

    private final IPlanService iplanService;
    private final PlanService planService;

    @GetMapping
    @Operation(summary = "Get all plans")
    public ResponseEntity<Page<PlanResponseDto>> getAllPlans(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<PlanResponseDto> planPage = iplanService.getPlans(PageRequest.of(page, size));
        return ResponseEntity.ok().body(planPage);
    }

    @PostMapping
    @Operation(summary = "Create a new plan")
    public ResponseEntity<PlanResponseDto> createPatient(@Validated @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto createdPlan = iplanService.createPlan(planRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update an existing plan")
    public ResponseEntity<PlanResponseDto> updatePlan(@PathVariable String id, @Validated @RequestBody PlanRequestDto planRequestDto) {
        PlanResponseDto updatedPlan = iplanService.updatePlan(id, planRequestDto);
        return ResponseEntity.ok().body(updatedPlan);

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a plan")
    public ResponseEntity<Void> deletePlan(@PathVariable String id) {
        iplanService.deletePlan(id);
        return ResponseEntity.noContent().build();
    }




    @PutMapping("/{planId}/inherit-from/{basePlanId}")
    public ResponseEntity<Void> inheritFromBase(@PathVariable String planId, @PathVariable String basePlanId) {
        planService.inheritPlan(planId, basePlanId);
        return ResponseEntity.noContent().build();
    }


}
