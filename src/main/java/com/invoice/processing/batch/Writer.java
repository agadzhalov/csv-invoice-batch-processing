package com.invoice.processing.batch;

import com.invoice.processing.model.Invoice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Writer implements ItemWriter<Invoice> {

    private static final Logger logger = LoggerFactory.getLogger(Writer.class);

    @Override
    public void write(List<? extends Invoice> list) throws Exception {
        logger.info("[WRITER]: " + list);
    }
}
