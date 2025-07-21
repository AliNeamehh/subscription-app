package com.example.invoiceservice.service;


import com.example.invoiceservice.dto.InvoiceRequest;
import com.example.invoiceservice.model.Invoice;
import com.example.invoiceservice.model.enums.InvoiceStatus;
import com.example.invoiceservice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService implements IInvoiceService {

    private final  InvoiceRepository invoiceRepository;

    public void createInvoice(InvoiceRequest invoiceRequest){

        Invoice invoice = new Invoice();
        invoice.setTenantId(invoiceRequest.getTenantId());
        invoice.setSubscriptionId(invoiceRequest.getSubscriptionId());
        invoice.setDueDate(invoiceRequest.getDueDate());
        invoice.setAmount(invoiceRequest.getAmount());
        invoice.setStatus(InvoiceStatus.UNPAID);
        invoiceRepository.save(invoice);

    }

}
