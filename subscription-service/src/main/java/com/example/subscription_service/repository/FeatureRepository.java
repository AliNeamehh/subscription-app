package com.example.subscription_service.repository;

import com.example.subscription_service.model.Feature;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureRepository extends MongoRepository<Feature, String> {

}
