package com.example.subscription_service.repository;

import com.example.subscription_service.model.App;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRepository  extends MongoRepository<App, String> {
}
