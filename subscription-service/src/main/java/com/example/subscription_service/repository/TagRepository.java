package com.example.subscription_service.repository;

import com.example.subscription_service.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {

    boolean existsByName(String name);

}
