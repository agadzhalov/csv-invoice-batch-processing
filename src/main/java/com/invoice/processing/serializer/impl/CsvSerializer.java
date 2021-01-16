package com.invoice.processing.serializer.impl;

import com.invoice.processing.model.Invoice;
import com.invoice.processing.serializer.ICsvSerializer;
import org.springframework.stereotype.Component;

@Component
public class CsvSerializer implements ICsvSerializer {

    @Override
    public String writeCSVLine(Invoice invoice) {
        return invoice.getBuyer() + ", "
                + invoice.getImageName() + ", "
                + invoice.getInvoiceImage() + ", "
                + invoice.getInvoiceDueDate() + ", "
                + invoice.getInvoiceNumber() + ", "
                + invoice.getInvoiceAmount() + ", "
                + invoice.getInvoiceCurrency() + ", "
                + invoice.getInvoiceStatus() + ", "
                + invoice.getSupplier();
    }

    @Override
    public String writeCSVHeaderLine() {
        return "buyer, "
                + "image_name, "
                + "invoice_image, "
                + "invoice_due_date, "
                + "invoice_number, "
                + "invoice_amount, "
                + "invoice_currency, "
                + "invoice_status, "
                + "supplier";
    }
}
