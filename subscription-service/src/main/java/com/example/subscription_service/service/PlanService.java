package com.example.subscription_service.service;


import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.exception.PlanNotFoundException;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.save(planMapper.toModel(planRequestDto));


        if (plan.getBasePlanId() != null && !plan.getBasePlanId().isEmpty()) {
            inheritPlan(plan.getId(), plan.getBasePlanId());
            plan = planRepository.findById(plan.getId()).orElseThrow(() -> new PlanNotFoundException("Plan not found after inheritance"));
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


}
