package com.invoice.processing.batch;

import com.invoice.processing.model.Invoice;
import com.invoice.processing.parser.ICsvParser;
import com.invoice.processing.parser.IXmlParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<Invoice, Invoice> {

    @Autowired
    private ICsvParser csvParseService;

    @Autowired
    private IXmlParser xmlParseService;

    private JobParameters jobParameters;

    private static final Logger logger = LoggerFactory.getLogger(Processor.class);

    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {
        jobParameters = stepExecution.getJobParameters();
    }

    @Override
    public Invoice process(Invoice invoice) throws Exception {
        logger.info("[PROCESSOR]: " + invoice);

        switch (jobParameters.getParameters().get("format").toString()) {
            case "csv":
                csvParseService.parseCsvFile(invoice);
                break;
            case "xml":
                xmlParseService.parseXml(invoice);
                break;
            default:
                break;
        }

        return invoice;
    }

}
