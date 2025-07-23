package com.example.invoiceservice.controller;


import com.example.invoiceservice.dto.PaymentRequest;
import com.example.invoiceservice.service.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final ITransactionService transactionService;


    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody PaymentRequest transaction){

         transactionService.processPayment(transaction);

         return ResponseEntity.ok().build();


    }



}
