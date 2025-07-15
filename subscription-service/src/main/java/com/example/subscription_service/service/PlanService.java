package com.example.subscription_service.service;


import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.PlanResponseDto;
import com.example.subscription_service.mapper.PlanMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.repository.PlanRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository;

    public PlanResponseDto createPlan(PlanRequestDto planRequestDto) {
        Plan plan= planRepository.save(PlanMapper.toModel(planRequestDto));
        return PlanMapper.toDto(plan);
    }




}
