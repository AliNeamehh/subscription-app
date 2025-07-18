package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    public Page<PlanResponseDto> getPlans(Pageable pageable) {
        Page<Plan> tagPage = planRepository.findAll(pageable);
        return tagPage.map(planMapper::toDto);

    }


    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.save(planMapper.toModel(planRequestDto));

        if (plan.getBasePlanId() != null && !plan.getBasePlanId().isEmpty()) {

            if (!planRepository.existsById(plan.getBasePlanId())) {
                throw new NotFoundException("BasePlan not found ");
            }
            inheritPlan(plan.getId(), plan.getBasePlanId());
            plan = planRepository.findById(plan.getId()).orElseThrow(() -> new NotFoundException("Plan not found after inheritance"));
        }
        return planMapper.toDto(plan);
    }


    public void inheritPlan(String basePlanId, String inheritedPlanId) {

        Optional<Plan> optionalBasePlan = planRepository.findById(basePlanId);
        Optional<Plan> optionalInheritPlan = planRepository.findById(inheritedPlanId);

        if (optionalBasePlan.isEmpty()) {
            throw new NotFoundException("Base plan not found");
        }

        if (optionalInheritPlan.isEmpty()) {
            throw new NotFoundException("Inherited plan not found");
        }


        Plan basePlan = optionalBasePlan.get();
        Plan inheritedPlan = optionalInheritPlan.get();

        Set<String> mergedTagIds = new HashSet<>(basePlan.getTagIds());
        mergedTagIds.addAll(inheritedPlan.getTagIds());

        basePlan.setTagIds(new ArrayList<>(mergedTagIds));

        planRepository.save(basePlan);
    }

    public PlanResponseDto updatePlan(String planId, PlanRequestDto planRequestDto) {

        if (planRequestDto.getBasePlanId() != null && !planRequestDto.getBasePlanId().isEmpty()) {
            if (!planRepository.existsById(planRequestDto.getBasePlanId())) {
                throw new NotFoundException("BasePlan not found ");
            }
        }

        Plan plan = planRepository.findById(planId).orElseThrow(() -> new NotFoundException("Plan not found"));
        Plan updatedPlan = planMapper.toModel(planRequestDto);
        updatedPlan.setId(planId);
        planRepository.save(updatedPlan);
        return planMapper.toDto(updatedPlan);

    }

    public void deletePlan(String planId) {
        planRepository.deleteById(planId);
    }

}
