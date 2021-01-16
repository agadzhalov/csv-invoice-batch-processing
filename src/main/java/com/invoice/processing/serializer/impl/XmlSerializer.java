package com.invoice.processing.serializer.impl;

import com.invoice.processing.model.Invoice;
import com.invoice.processing.util.ImageDecoder;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.invoice.processing.serializer.IXmlSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class XmlSerializer implements IXmlSerializer {

    @Value("${invoice.xml.output.imgs}")
    private String invoiceXmlOutputImages;

    private static final Logger logger = LoggerFactory.getLogger(XmlSerializer.class);

    @Override
    public void createNewXMLFile(DocumentBuilder docBuilder, File xmlFile, Invoice invoice) {
        try {
            Document xmlDoc = docBuilder.newDocument();

            Element rootElement = xmlDoc.createElement("invoices");
            Element invoiceElement = getInvoiceElement(xmlDoc, invoice);

            rootElement.appendChild(invoiceElement);
            xmlDoc.appendChild(rootElement);

            ImageDecoder.decode(invoice.getInvoiceImage(), invoiceXmlOutputImages + invoice.getImageName());

            OutputFormat outputFormat = new OutputFormat(xmlDoc);
            outputFormat.setIndenting(true);

            // writer
            FileOutputStream outputStream = new FileOutputStream(xmlFile);
            XMLSerializer serializer = new XMLSerializer(outputStream, outputFormat);
            serializer.serialize(xmlDoc);
            outputStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void addInvoiceXMLElement(DocumentBuilder docBuilder, File xmlFile, Invoice invoice) {
        try {
            Document xmlDoc = docBuilder.parse(xmlFile);
            Element root = xmlDoc.getDocumentElement();
            Collection<Invoice> invoices = new ArrayList<>();
            invoices.add(invoice);

            ImageDecoder.decode(invoice.getInvoiceImage(), invoiceXmlOutputImages + invoice.getImageName());
            root.appendChild(getInvoiceElement(xmlDoc, invoice));

            DOMSource source = new DOMSource(xmlDoc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public Element getInvoiceElement(Document xmlDoc, Invoice invoice) {
        Element invoiceElement = xmlDoc.createElement("invoice");

        Element invoiceBuyer = xmlDoc.createElement("buyer");
        invoiceBuyer.appendChild(xmlDoc.createTextNode(invoice.getBuyer()));

        Element imageName = xmlDoc.createElement("image_name");
        imageName.appendChild(xmlDoc.createTextNode(invoice.getImageName()));

        Element invoiceDueDate = xmlDoc.createElement("invoice_due_date");
        invoiceDueDate.appendChild(xmlDoc.createTextNode(invoice.getInvoiceDueDate()));

        Element invoiceNumber = xmlDoc.createElement("invoice_number");
        invoiceNumber.appendChild(xmlDoc.createTextNode(invoice.getInvoiceNumber()));

        Element invoiceAmount = xmlDoc.createElement("invoice_amount");
        invoiceAmount.appendChild(xmlDoc.createTextNode(invoice.getInvoiceAmount()));

        Element invoiceCurrency = xmlDoc.createElement("invoice_currency");
        invoiceCurrency.appendChild(xmlDoc.createTextNode(invoice.getInvoiceCurrency()));

        Element invoiceStatus = xmlDoc.createElement("invoice_status");
        invoiceStatus.appendChild(xmlDoc.createTextNode(invoice.getInvoiceStatus()));

        Element supplier = xmlDoc.createElement("supplier");
        supplier.appendChild(xmlDoc.createTextNode(invoice.getSupplier()));

        invoiceElement.appendChild(invoiceBuyer);
        invoiceElement.appendChild(imageName);
        invoiceElement.appendChild(invoiceDueDate);
        invoiceElement.appendChild(invoiceNumber);
        invoiceElement.appendChild(invoiceAmount);
        invoiceElement.appendChild(invoiceCurrency);
        invoiceElement.appendChild(invoiceStatus);
        invoiceElement.appendChild(supplier);

        return invoiceElement;
    }

}
