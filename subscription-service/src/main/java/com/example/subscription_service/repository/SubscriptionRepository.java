package com.example.subscription_service.repository;

import com.example.subscription_service.model.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

}
