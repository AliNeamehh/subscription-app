package com.example.invoiceservice.controller;


import com.example.invoiceservice.dto.InvoiceRequest;
import com.example.invoiceservice.service.IInvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private  final IInvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Void> createInvoice(@RequestBody InvoiceRequest invoiceRequest){
        invoiceService.createInvoice(invoiceRequest);
        return ResponseEntity.ok().build();

    }




}
