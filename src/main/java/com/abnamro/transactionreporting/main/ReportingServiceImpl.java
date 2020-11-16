package com.abnamro.transactionreporting.main;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.processor.ReportProcessor;
import com.abnamro.transactionreporting.services.reader.FileReader;
import com.abnamro.transactionreporting.services.writer.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static com.abnamro.transactionreporting.constant.TransactionReportingConstants.CSV_OUTPUT_FILE;

/**
 * Orchestration file. Responsible of reading input flat feed, formatting report and writing to CSV file.
 */
@Component
@Slf4j
public class ReportingServiceImpl implements ReportingService {

    private final FileReader fileReader;
    private final ReportProcessor reportProcessor;
    private final FileWriter fileWriter;

    /**
     * Constructor initialization.
     *
     * @param fileReader      {@link FileReader}
     * @param reportProcessor {@link ReportProcessor}
     * @param fileWriter      {@link FileWriter}
     */
    public ReportingServiceImpl(final FileReader fileReader, final ReportProcessor reportProcessor, final FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.reportProcessor = reportProcessor;
        this.fileWriter = fileWriter;
    }

    /**
     * Reads input feed, formats it and writes output.
     *
     * @throws IOException
     */
    @Override
    public void process() throws IOException {
        log.info("Transaction Reporting :: Started");
        List<ReportModel> unformattedReport = fileReader.readFlatFile();
        List<ReportModel> formatedReport = reportProcessor.formatReport(unformattedReport);
        fileWriter.writeCSV(CSV_OUTPUT_FILE, formatedReport);
        log.info("Transaction Reporting :: Finished");
    }
}
