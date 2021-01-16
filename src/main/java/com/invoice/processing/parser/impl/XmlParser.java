package com.invoice.processing.parser.impl;

import com.invoice.processing.model.Invoice;
import com.invoice.processing.parser.IXmlParser;
import com.invoice.processing.serializer.IXmlSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

@Service
public class XmlParser implements IXmlParser {

    @Value("${invoice.xml.output.folder}")
    private String invoiceXmlOutputFolder;

    @Autowired
    private IXmlSerializer xmlSerializer;


    @Override
    public void parseXml(Invoice invoice) {
        try {
            File xmlFile =  new File(invoiceXmlOutputFolder + invoice.getBuyer() + ".xml");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            if (!xmlFile.exists()) {
                xmlSerializer.createNewXMLFile(docBuilder, xmlFile, invoice);
            } else {
                xmlSerializer.addInvoiceXMLElement(docBuilder, xmlFile, invoice);
            }

        } catch (Exception ex) {

        }
    }

}
