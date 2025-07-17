package com.example.subscription_service.repository;

import com.example.subscription_service.model.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepository extends MongoRepository<Plan, String> {
    boolean existsById(String id);

}
