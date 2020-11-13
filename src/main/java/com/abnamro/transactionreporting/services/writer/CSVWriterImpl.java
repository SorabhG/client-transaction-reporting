package com.abnamro.transactionreporting.services.writer;


import com.abnamro.transactionreporting.constant.TransactionReportingConstants;
import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.slf4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for writing java objects to CSV file.
 */
@Slf4j
public class CSVWriterImpl implements FileWriter {

    public Logger getLogger() {
        return log;
    }

    /**
     * @param csvName     {@link String }
     * @param reportModels {@link List}
     * @throws IOException
     */
    @Override
    public void writeCSV(final String csvName, final List<ReportModel> reportModels) throws IOException {
        getLogger().info("Writing Output file :: Started");
        final List<String> header = new ArrayList<>();
        header.add(TransactionReportingConstants.HEADER_CLIENT_INFORMATION);
        header.add(TransactionReportingConstants.HEADER_PRODUCT_INFORMATION);
        header.add(TransactionReportingConstants.HEADER_TOTAL_TRANSACTION_AMOUNT);
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvName));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(header.toArray(new String[0])));
            reportModels.forEach(reportModel -> {
                try {
                    csvPrinter.printRecord(reportModel.getClientInformation(),
                            reportModel.getProductInformation(),
                            reportModel.getTransactionAmount());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            csvPrinter.flush();
            getLogger().info("Writing Output file :: Finished");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
