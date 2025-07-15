package com.example.subscription_service.repository;

import com.example.subscription_service.model.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository  extends MongoRepository<Tenant, String> {
}
