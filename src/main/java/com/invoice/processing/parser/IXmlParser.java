package com.invoice.processing.parser;

import com.invoice.processing.model.Invoice;

public interface IXmlParser {

    void parseXml(Invoice invoice);

}
