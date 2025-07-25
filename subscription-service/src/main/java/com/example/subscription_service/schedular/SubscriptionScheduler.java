package com.example.subscription_service.schedular;


import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.repository.SubscriptionRepository;
import com.example.subscription_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionScheduler {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionScheduler.class);

    private final SubscriptionRepository subscriptionRepository;

    private final SubscriptionService  subscriptionService;

    @Scheduled(cron = "*/5 * * * * ?")
    public void checkInactiveSubscription(){

        int page=0;
        int size=100;
        Page<Subscription> subscriptionPage;

        do{

            Pageable pageable= PageRequest.of(page,size);

            subscriptionPage=subscriptionService.getCancelledSubscriptionsPaged(pageable);

            subscriptionRepository.deleteAll(subscriptionPage);

            for(Subscription subscription:subscriptionPage.getContent()){
                logger.info("this subscription with tis id is active"+subscription.getId());
            }

          page++;

        }while (subscriptionPage.hasNext());



    }





}
