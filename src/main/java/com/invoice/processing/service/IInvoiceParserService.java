package com.invoice.processing.service;

import com.invoice.processing.model.Response;
import org.springframework.stereotype.Component;

@Component
public interface IInvoiceParserService {

    Response batchFileParsing(String destinationFormat);

}
