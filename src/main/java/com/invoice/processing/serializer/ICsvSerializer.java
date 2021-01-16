package com.invoice.processing.serializer;

import com.invoice.processing.model.Invoice;

public interface ICsvSerializer {

    String writeCSVLine(Invoice invoice);

    String writeCSVHeaderLine();
}
