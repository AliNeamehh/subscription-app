package com.example.subscription_service.controller;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.model.enums.SubscriptionStatus;
import com.example.subscription_service.service.ISubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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

    @PostMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable String id, @RequestParam SubscriptionStatus status){

      subscriptionService.updateStatus(id, status);
      return ResponseEntity.ok().build();

    }

   @PostMapping("/import-csv")
    public ResponseEntity<Void> importCsv(@RequestParam("file") MultipartFile file) throws Exception {
        File temp =File.createTempFile("subscription",".csv");
        file.transferTo(temp);
        subscriptionService.insertFromCsv(temp);
        return ResponseEntity.ok().build();
   }



}
