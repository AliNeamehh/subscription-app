package com.example.subscription_service.service;


import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.exception.PlanNotFoundException;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;


    public List<PlanResponseDto> getPlans(){

        List<Plan> plans= planRepository.findAll();

        return plans.stream().map(planMapper::toDto).toList();

    }




    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.save(planMapper.toModel(planRequestDto));



        if (plan.getBasePlanId() != null && !plan.getBasePlanId().isEmpty()) {
            inheritPlan(plan.getId(), plan.getBasePlanId());
            plan = planRepository.findById(plan.getId()).orElseThrow(() -> new PlanNotFoundException("Plan not found after inheritance"));
        }

        if(!planRepository.existsById(plan.getBasePlanId())) {

            throw new PlanNotFoundException("BasePlan not found ");
        }

        


        return planMapper.toDto(plan);
    }


    public void inheritPlan(String basePlanId, String inheritedPlanId) {

        Optional<Plan> optionalBasePlan = planRepository.findById(basePlanId);
        Optional<Plan> optionalInheritPlan = planRepository.findById(inheritedPlanId);

        if (optionalBasePlan.isEmpty()) {
            throw new PlanNotFoundException("Base plan not found");
        }

        if (optionalInheritPlan.isEmpty()) {
            throw new PlanNotFoundException("Inherited plan not found");
        }

        Plan basePlan = optionalBasePlan.get();
        Plan inheritedPlan = optionalInheritPlan.get();

        Set<String> mergedTagIds = new HashSet<>(basePlan.getTagIds());
        mergedTagIds.addAll(inheritedPlan.getTagIds());

        basePlan.setTagIds(new ArrayList<>(mergedTagIds));

        planRepository.save(basePlan);
    }

    public PlanResponseDto updatePlan(String planId, PlanRequestDto planRequestDto) {

        Plan plan = planRepository.findById(planId).orElseThrow(() -> new PlanNotFoundException("Plan not found"));

        plan.setName(planRequestDto.getName());
        plan.setDescription(planRequestDto.getDescription());
        plan.setPrice(planRequestDto.getPrice());
        plan.setBasePlanId(planRequestDto.getBasePlanId());
        plan.setBillingCycle(planRequestDto.getBillingCycle());
        plan.setTagIds(planRequestDto.getTagIds());
        plan.setTrialDays(planRequestDto.getTrialDays());

        planRepository.save(plan);

        return null;
    }

    public void deletePlan(String planId) {
        planRepository.deleteById(planId);
    }

}
