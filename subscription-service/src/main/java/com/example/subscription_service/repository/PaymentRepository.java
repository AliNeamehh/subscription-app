package com.example.subscription_service.repository;

import com.example.subscription_service.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository  extends MongoRepository<Payment, String> {
}
