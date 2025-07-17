package com.example.subscription_service.service;

import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.SubscriptionMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.model.Tenant;
import com.example.subscription_service.repository.PlanRepository;
import com.example.subscription_service.repository.SubscriptionRepository;
import com.example.subscription_service.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final TenantRepository tenantRepository;
    private final PlanRepository planRepository;

    private final SubscriptionMapper subscriptionMapper;


    public SubscribeResponseDto subscribeTenantTOPlan(SubscribeRequestDto subscribeRequestDto) {

        Optional<Tenant> optionalTenant = tenantRepository.findById(subscribeRequestDto.getTenantId());
        Optional<Plan> optionalPlan = planRepository.findById(subscribeRequestDto.getPlanId());

        if (optionalTenant.isEmpty()) {
            throw new NotFoundException("Tenant is Not Found");
        }
        if (optionalPlan.isEmpty()) {
            throw new NotFoundException("Plan is Not Found");
        }
        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toModel(subscribeRequestDto));
        return subscriptionMapper.toResponseDto(subscription);

    }

}
