package com.invoice.processing.service.impl;

import com.invoice.processing.exception.DestinationFormatException;
import com.invoice.processing.model.Response;
import com.invoice.processing.service.IInvoiceParserService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceParserService implements IInvoiceParserService {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Override
    public Response batchFileParsing(String destinationFormat) {

        try {
            if (!destinationFormat.equalsIgnoreCase("csv") &&
                !destinationFormat.equalsIgnoreCase("xml"))
                throw new DestinationFormatException("Wrong destination format");

            Map<String, JobParameter> mapParameters = new HashMap<>();
            mapParameters.put("time", new JobParameter(System.currentTimeMillis()));
            mapParameters.put("format", new JobParameter(destinationFormat));
            JobParameters parameters = new JobParameters(mapParameters);
            JobExecution jobExecution = jobLauncher.run(job, parameters);

            return new Response(jobExecution.getStatus().toString(), destinationFormat, HttpStatus.OK);
        } catch (JobParametersInvalidException e) {
            return new Response(e.getMessage(), destinationFormat, HttpStatus.BAD_REQUEST);
        } catch (JobExecutionAlreadyRunningException e) {
            return new Response(e.getMessage(), destinationFormat, HttpStatus.BAD_REQUEST);
        } catch (JobRestartException e) {
            return new Response(e.getMessage(), destinationFormat, HttpStatus.BAD_REQUEST);
        } catch (JobInstanceAlreadyCompleteException e) {
            return new Response(e.getMessage(), destinationFormat, HttpStatus.BAD_REQUEST);
        } catch (DestinationFormatException e) {
            return new Response(e.getMessage(), destinationFormat, HttpStatus.BAD_REQUEST);
        }
    }
}
