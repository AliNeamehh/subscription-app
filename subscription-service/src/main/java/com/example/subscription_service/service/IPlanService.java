package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlanService {

     Page<PlanResponseDto> getPlans(Pageable pageable);

    PlanResponseDto createPlan(PlanRequestDto planRequestDto);

    void inheritPlan(String basePlanId, String inheritedPlanId);

    PlanResponseDto updatePlan(String planId, PlanRequestDto planRequestDto);

    void deletePlan(String planId);

}
