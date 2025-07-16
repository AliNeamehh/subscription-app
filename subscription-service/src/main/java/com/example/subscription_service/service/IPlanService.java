package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;

import java.util.List;

public interface IPlanService {

    List<PlanResponseDto> getPlans();

    PlanResponseDto createPlan(PlanRequestDto planRequestDto);

    void inheritPlan(String basePlanId, String inheritedPlanId);

    PlanResponseDto updatePlan(String planId, PlanRequestDto planRequestDto);

    void deletePlan(String planId);

}
