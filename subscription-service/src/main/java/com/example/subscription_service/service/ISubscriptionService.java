package com.example.subscription_service.service;

import com.example.subscription_service.dto.PlanRequestDto;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.model.enums.SubscriptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.File;

public interface ISubscriptionService {

    SubscribeResponseDto subscribeTenantTOPlan(SubscribeRequestDto subscribeRequestDto);

       void createInvoiceForSubscription(Subscription sub);

      void updateStatus(String Id, SubscriptionStatus status);

       void insertFromCsv(File file) throws Exception;


}
