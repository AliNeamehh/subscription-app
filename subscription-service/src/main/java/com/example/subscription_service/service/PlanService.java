package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import com.example.subscription_service.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class PlanService implements IPlanService {

    private final ITagService tagService;

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;
    private final TagRepository tagRepository;


    public Page<PlanResponseDto> getPlans(Pageable pageable) {
        Page<Plan> tagPage = planRepository.findAll(pageable);
        return tagPage.map(planMapper::toDto);

    }


    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {

        tagService.validateTag(planRequestDto.getTagIds());

        if (planRequestDto.getBasePlanId() != null && !planRequestDto.getBasePlanId().isEmpty()) {
            if (!planRepository.existsById(planRequestDto.getBasePlanId())) {
                throw new NotFoundException("BasePlan not found ");
            }
        }
        Plan plan = planRepository.save(planMapper.toModel(planRequestDto));
        return planMapper.toDto(plan);
    }




    public PlanResponseDto updatePlan(String planId, PlanRequestDto planRequestDto) {

        Plan plan = planRepository.findById(planId).orElseThrow(() -> new NotFoundException("Plan not found"));

        tagService.validateTag(planRequestDto.getTagIds());

        if (planRequestDto.getBasePlanId() != null && !planRequestDto.getBasePlanId().isEmpty()) {
            if (!planRepository.existsById(planRequestDto.getBasePlanId())) {
                throw new NotFoundException("BasePlan not found ");
            }
        }

        Plan updatedPlan = planMapper.toModel(planRequestDto);
        updatedPlan.setId(planId);
        planRepository.save(updatedPlan);
        return planMapper.toDto(updatedPlan);

    }

    public void deletePlan(String planId) {
        planRepository.deleteById(planId);
    }

}
