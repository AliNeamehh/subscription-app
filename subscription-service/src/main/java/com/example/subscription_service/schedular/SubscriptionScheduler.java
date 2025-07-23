package com.example.subscription_service.schedular;


import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubscriptionScheduler {

    private final SubscriptionService  subscriptionService;

    @Scheduled(cron = "*/2 * * * * ?")
    public void checkInactiveSubscription(){

        int page=0;
        int size=100;
        Page<Subscription> subscriptionPage;

        do{

            Pageable pageable= PageRequest.of(page,size);

            subscriptionPage=subscriptionService.getInactiveSubscriptionsPaged(pageable);

            for(Subscription subscription:subscriptionPage.getContent()){
                System.out.println("this subscription with tis id is not active"+subscription.getId()   );
            }

          page++;

        }while (subscriptionPage.hasNext());



    }





}
