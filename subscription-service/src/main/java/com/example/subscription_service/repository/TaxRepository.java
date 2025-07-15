package com.example.subscription_service.repository;


import com.example.subscription_service.model.Tax;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaxRepository extends MongoRepository<Tax, String> {

}
