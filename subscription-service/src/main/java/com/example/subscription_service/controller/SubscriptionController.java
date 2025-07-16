package com.example.subscription_service.controller;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.service.ISubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
@Tag(name = "Subscription Management", description = "APIs for managing subscriptions ")
public class SubscriptionController {


    private final ISubscriptionService  subscriptionService;

    @PostMapping
    @Operation(summary = "Create a new subscription")
    public ResponseEntity<SubscribeResponseDto> createSubscription( @Validated @RequestBody SubscribeRequestDto subscribeRequestDto) {

        SubscribeResponseDto createdSubscription=subscriptionService.subscribeTenantTOPlan(subscribeRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }



}
