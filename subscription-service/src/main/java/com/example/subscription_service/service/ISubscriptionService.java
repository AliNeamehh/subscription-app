package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.model.Subscription;

public interface ISubscriptionService {

    SubscribeResponseDto subscribeTenantTOPlan(SubscribeRequestDto subscribeRequestDto);

       void createInvoiceForSubscription(Subscription sub);

}
