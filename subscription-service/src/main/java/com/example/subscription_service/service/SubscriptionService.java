package com.example.subscription_service.service;

import com.example.subscription_service.dto.InvoiceRequest;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.SubscriptionMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.repository.PlanRepository;
import com.example.subscription_service.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {


    private final   RestTemplate restTemplate;

    private final SubscriptionRepository subscriptionRepository;

    private final PlanRepository planRepository;

    private final SubscriptionMapper subscriptionMapper;


    public SubscribeResponseDto subscribeTenantTOPlan(SubscribeRequestDto subscribeRequestDto) {


        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toModel(subscribeRequestDto));


        return subscriptionMapper.toResponseDto(subscription);

    }


    public void createInvoiceForSubscription(Subscription sub) {

        Plan plan = planRepository.findById(sub.getPlanId()).get();

        InvoiceRequest invoiceRequest = new InvoiceRequest(
                sub.getTenantId(), sub.getId(),
                plan.getPrice(),
                sub.getEndDate());

        restTemplate.postForEntity("http://localhost:8081/invoices", invoiceRequest, Void.class);

    }



}
