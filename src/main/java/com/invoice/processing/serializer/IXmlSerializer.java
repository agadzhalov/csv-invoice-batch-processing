package com.invoice.processing.serializer;

import com.invoice.processing.model.Invoice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;

public interface IXmlSerializer {

    void createNewXMLFile(DocumentBuilder docBuilder, File xmlFile, Invoice invoice);

    void addInvoiceXMLElement(DocumentBuilder docBuilder, File xmlFile, Invoice invoice);

    Element getInvoiceElement(Document xmlDoc, Invoice invoice);

}
