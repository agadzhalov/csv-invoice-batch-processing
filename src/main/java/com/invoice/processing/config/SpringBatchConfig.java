package com.invoice.processing.config;

import com.invoice.processing.model.Invoice;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineCallbackHandler;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<Invoice> itemReader,
                   ItemProcessor<Invoice, Invoice> itemProcessor,
                   ItemWriter<Invoice> itemWriter) {

        Step step = stepBuilderFactory.get("INVOICE_STEP")
                .<Invoice, Invoice>chunk(100)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();

        Job job = jobFactory.get("INVOICE_JOB")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

        return job;
    }


    @Bean
    @StepScope
    public FlatFileItemReader<Invoice> invoiceItemReader(@Value("${invoice.input.file}") Resource resource) {
        DefaultLineMapper<Invoice> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();

        lineMapper.setLineTokenizer(delimitedLineTokenizer);
        lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<Invoice>() {
            {
                setTargetType(Invoice.class);
            }
        });

        LineCallbackHandler skippedLineCallback = new LineCallbackHandler() {
            @Override
            public void handleLine(String line) {
                //@TODO deep research on Spring Batch for dynamic added columns etc
                String[] headerColumns = line.split(",");
                delimitedLineTokenizer.setNames(headerColumns);
            }
        };

        return new FlatFileItemReaderBuilder<Invoice>()
                .name("INVOICE_ITEM_READER")
                .resource(resource)
                .linesToSkip(1)
                .skippedLinesCallback(skippedLineCallback)
                .lineMapper(lineMapper)
                .build();
    }

}
