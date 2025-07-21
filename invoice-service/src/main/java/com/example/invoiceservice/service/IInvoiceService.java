package com.example.invoiceservice.service;

import com.example.invoiceservice.dto.InvoiceRequest;

public interface IInvoiceService {

    void createInvoice(InvoiceRequest invoiceRequest);

}
