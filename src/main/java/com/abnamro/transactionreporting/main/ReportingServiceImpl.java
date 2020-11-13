package com.abnamro.transactionreporting.main;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.processor.ReportProcessor;
import com.abnamro.transactionreporting.services.processor.ReportProcessorImpl;
import com.abnamro.transactionreporting.services.reader.FileReader;
import com.abnamro.transactionreporting.services.reader.FileReaderImpl;
import com.abnamro.transactionreporting.services.writer.CSVWriterImpl;
import com.abnamro.transactionreporting.services.writer.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.abnamro.transactionreporting.constant.TransactionReportingConstants.CSV_OUTPUT_FILE;

@Component
@Slf4j
public class ReportingServiceImpl implements ReportingService{

    @Override
    public void process() throws IOException {
        log.info("Transaction Reporting :: Started");
        FileReader fileReaderImpl = new FileReaderImpl();
        ReportProcessor reportProcessor = new ReportProcessorImpl();
        FileWriter csvWriterImpl = new CSVWriterImpl();
        List<ReportModel> reportModels = fileReaderImpl.readFlatFile();
        List<ReportModel> formatedReport = reportProcessor.formatReport(reportModels);
        csvWriterImpl.writeCSV(CSV_OUTPUT_FILE, formatedReport);
        log.info("Transaction Reporting :: Finished");
    }
}
