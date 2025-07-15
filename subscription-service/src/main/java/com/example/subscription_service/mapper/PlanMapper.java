package com.example.subscription_service.mapper;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.model.Plan;

public class PlanMapper {

    public static Plan toModel(PlanRequestDto planRequestDto) {

        Plan plan = new Plan();
        plan.setName(planRequestDto.getName());
        plan.setDescription(planRequestDto.getDescription());
        plan.setPrice(planRequestDto.getPrice());
        plan.setBillingCycle(planRequestDto.getBillingCycle());
        plan.setTagIds(planRequestDto.getTagIds());
        plan.setTrialDays(planRequestDto.getTrialDays());
        plan.setBasePlanId(planRequestDto.getBasePlanId());

        return plan;
    }

    public static PlanResponseDto toDto(Plan plan) {

            PlanResponseDto planResponseDto = new PlanResponseDto();

            planResponseDto.setId(plan.getId());
            planResponseDto.setName(plan.getName());
            planResponseDto.setDescription(plan.getDescription());
            planResponseDto.setPrice(plan.getPrice());
            planResponseDto.setBillingCycle(plan.getBillingCycle());
            planResponseDto.setTagIds(plan.getTagIds());
            planResponseDto.setTrialDays(plan.getTrialDays());
            planResponseDto.setBasePlanId(plan.getBasePlanId());
            return planResponseDto;


    }

}
