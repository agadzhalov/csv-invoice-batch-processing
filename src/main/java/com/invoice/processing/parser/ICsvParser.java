package com.invoice.processing.parser;

import com.invoice.processing.model.Invoice;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface ICsvParser {

    void parseCsvFile(Invoice invoice) throws Exception;

    File createModifyInvoiceFile(Invoice invoice, File outFile) throws Exception;
}
