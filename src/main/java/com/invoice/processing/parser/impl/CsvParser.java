package com.invoice.processing.parser.impl;

import com.invoice.processing.model.Invoice;
import com.invoice.processing.serializer.ICsvSerializer;
import com.invoice.processing.parser.ICsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

@Service
public class CsvParser implements ICsvParser {

    @Autowired
    ICsvSerializer csvSerializer;

    @Value("${invoice.csv.output.folder}")
    private String invoiceCsvOutputFolder;

    @Override
    public void parseCsvFile(Invoice invoice) throws Exception {
        File outFile = new File(invoiceCsvOutputFolder + invoice.getBuyer() + ".csv");
        createModifyInvoiceFile(invoice, outFile);
    }

    @Override
    public File createModifyInvoiceFile(Invoice invoice, File outFile) throws Exception {
        FileWriter fw = new FileWriter(outFile, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        if (!outFile.exists() || outFile.length() == 0) {
            pw.println(csvSerializer.writeCSVHeaderLine());
            pw.println(csvSerializer.writeCSVLine(invoice));
            pw.close();
        } else {
            pw.println(csvSerializer.writeCSVLine(invoice));
            pw.close();
        }

        return outFile;
    }

}
