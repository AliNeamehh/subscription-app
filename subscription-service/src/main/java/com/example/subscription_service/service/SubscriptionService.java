package com.example.subscription_service.service;

import com.example.subscription_service.dto.InvoiceRequest;
import com.example.subscription_service.dto.SubscribeRequestDto;
import com.example.subscription_service.dto.SubscribeResponseDto;
import com.example.subscription_service.exception.NotFoundException;
import com.example.subscription_service.mapper.SubscriptionMapper;
import com.example.subscription_service.model.Plan;
import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.model.enums.SubscriptionStatus;
import com.example.subscription_service.repository.PlanRepository;
import com.example.subscription_service.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


@Service
@RequiredArgsConstructor
public class SubscriptionService implements ISubscriptionService {


    private final RestTemplate restTemplate;

    private final SubscriptionRepository subscriptionRepository;

    private final PlanRepository planRepository;

    private final SubscriptionMapper subscriptionMapper;


    public SubscribeResponseDto subscribeTenantTOPlan(SubscribeRequestDto subscribeRequestDto) {


        Subscription subscription = subscriptionRepository.save(subscriptionMapper.toModel(subscribeRequestDto));


        createInvoiceForSubscription(subscription);

        return subscriptionMapper.toResponseDto(subscription);

    }


    public void createInvoiceForSubscription(Subscription sub) {

        Plan plan = planRepository.findById(sub.getPlanId()).get();

        InvoiceRequest invoiceRequest = new InvoiceRequest(sub.getTenantId(), sub.getId(), plan.getPrice(), sub.getEndDate());

        restTemplate.postForEntity("http://localhost:8081/invoices", invoiceRequest, Void.class);

    }

    public void updateStatus(String Id, SubscriptionStatus status) {
        Subscription sub = subscriptionRepository.findById(Id).orElseThrow(() -> new NotFoundException("Subscription not found with id: " + Id));
        sub.setStatus(status);
        subscriptionRepository.save(sub);

    }


    public Page<Subscription> getInactiveSubscriptionsPaged(Pageable pageable) {

        return subscriptionRepository.findByStatus(SubscriptionStatus.INACTIVE, pageable);
    }

    public Page<Subscription> getCancelledSubscriptionsPaged(Pageable pageable) {

        return subscriptionRepository.findByStatus(SubscriptionStatus.CANCELLED, pageable);
    }



    public void insertFromCsv(File file) throws Exception {

        List<Subscription> subscriptions = new ArrayList<>();

        // step 1 Read Csv
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // split the readed line into array of strings
                String[] fields = line.split(",");

                Subscription subscription = Subscription.builder().tenantId(fields[1]).planId(fields[2]).startDate(Instant.parse(fields[3])).endDate(Instant.parse(fields[4])).status(SubscriptionStatus.valueOf(fields[5])).isAutoRenew(Boolean.parseBoolean(fields[6])).build();

                subscriptions.add(subscription);

            }

            // create a thread pool
            ExecutorService executor = Executors.newFixedThreadPool(5);
            List<Future<?>> futures = new ArrayList<>();

            //  chunk and submit

            int chunkSize = 10;

            for (int i = 0; i < subscriptions.size(); i += chunkSize) {

                int start = i;
                int end = Math.min(i + chunkSize, subscriptions.size());
                List<Subscription> chunk = subscriptions.subList(start, end);

                Future<?> future = executor.submit(() -> {


                    System.out.println("inserting chunck from start :" + start + " end:" + end + "by" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    subscriptionRepository.saveAll(chunk);

                });

                futures.add(future);

            }

            for (Future<?> future : futures) {
                future.get();
            }

            executor.shutdown();
            System.out.println("All subscriptions have been inserted");

        }


    }

}
