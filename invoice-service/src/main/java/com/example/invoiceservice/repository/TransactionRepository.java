package com.example.invoiceservice.repository;

import com.example.invoiceservice.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
}
