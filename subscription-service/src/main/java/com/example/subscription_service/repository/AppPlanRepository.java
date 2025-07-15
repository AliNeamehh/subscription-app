package com.example.subscription_service.repository;

import com.example.subscription_service.model.AppPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppPlanRepository extends MongoRepository<AppPlan, String> {
}
