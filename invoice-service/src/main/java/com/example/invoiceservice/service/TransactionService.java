package com.example.invoiceservice.service;


import com.example.invoiceservice.dto.PaymentRequest;
import com.example.invoiceservice.exception.NotFoundException;
import com.example.invoiceservice.exception.PaymentAmountMismatchException;
import com.example.invoiceservice.model.Invoice;
import com.example.invoiceservice.model.Transaction;
import com.example.invoiceservice.model.enums.InvoiceStatus;
import com.example.invoiceservice.model.enums.PaymentMethod;
import com.example.invoiceservice.repository.InvoiceRepository;
import com.example.invoiceservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService{

    private final InvoiceRepository invoiceRepository;
    private final TransactionRepository transactionRepository;
    private final RestTemplate restTemplate;

   public void processPayment(PaymentRequest paymentRequest){

       Invoice invoice=invoiceRepository.findById(paymentRequest.getInvoiceId()).orElseThrow(()->
               new NotFoundException("Invoice not found with this Id: " +
               paymentRequest.getInvoiceId()));

       double invoiceAmount=invoice.getAmount();
       double paymentAmount=paymentRequest.getAmount();

       if(paymentAmount!=invoiceAmount){
           throw new PaymentAmountMismatchException("Payment Amount Mismatch");
       }


       Transaction transaction= new Transaction();
       transaction.setInvoiceId(paymentRequest.getInvoiceId());
       transaction.setAmount(paymentRequest.getAmount());
       transaction.setPaymentDate(Instant.now());
       transaction.setMethod(paymentRequest.getPaymentMethod());
       transactionRepository.save(transaction);

       invoice.setStatus(InvoiceStatus.PAID);
       invoiceRepository.save(invoice);
       if (invoice.getSubscriptionId() == null) {
           throw new NotFoundException("Invoice is missing subscriptionId");
       }

       restTemplate.postForEntity("http://localhost:8080/subscriptions/" + invoice.getSubscriptionId() + "/status?status=ACTIVE",
               null,
               Void.class);


   }

}
