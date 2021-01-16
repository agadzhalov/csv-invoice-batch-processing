package com.invoice.processing.controller;

import com.invoice.processing.model.Response;
import com.invoice.processing.service.IInvoiceParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private IInvoiceParserService invoiceService;

    @GetMapping("parse")
    public ResponseEntity<Response> parseInvoice(@RequestParam("format") String destinationFormat)  {
        Response response = invoiceService.batchFileParsing(destinationFormat);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
