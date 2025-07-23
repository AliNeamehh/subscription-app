package com.example.subscription_service.repository;

import com.example.subscription_service.model.Subscription;
import com.example.subscription_service.model.enums.SubscriptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    Page<Subscription> findByStatus(SubscriptionStatus status, Pageable pageable);

}
