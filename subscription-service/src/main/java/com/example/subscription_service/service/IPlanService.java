package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;

public interface IPlanService {

    PlanResponseDto createPlan(PlanRequestDto planRequestDto);

    void inheritPlan(String basePlanId, String inheritedPlanId);

}
