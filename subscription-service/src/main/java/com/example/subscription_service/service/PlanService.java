package com.example.subscription_service.service;


import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    private final PlanMapper planMapper;

    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan = planRepository.save(planMapper.toModel(planRequestDto));
        return planMapper.toDto(plan);
    }


}
