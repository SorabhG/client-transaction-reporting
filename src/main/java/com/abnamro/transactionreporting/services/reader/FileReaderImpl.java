package com.abnamro.transactionreporting.services.reader;


import com.abnamro.transactionreporting.constant.TransactionReportingConstants;
import com.abnamro.transactionreporting.model.FileRecord;
import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.abnamro.transactionreporting.constant.TransactionReportingConstants.CLIENT_NUMBER_1234;


/**
 * This class is responsible for reading fixed length flat file and map to Java model.
 */
@Slf4j
@Component
public class FileReaderImpl implements FileReader {

    public Logger getLogger() {
        return log;
    }

    /**
     * Reads flat file.
     *
     * @return {@link List}
     * @throws IOException
     */
    @Override
    public List<ReportModel> readFlatFile() throws IOException {
        List<FileRecord> result = new ArrayList<>();
        List<ReportModel> result1 = new ArrayList<>();

        log.info("Reading Input file :: Started");

        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(TransactionReportingConstants.INPUT_FILE));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                if (CLIENT_NUMBER_1234.equalsIgnoreCase(currentLine.substring(7, 11).trim())) {
                    FileRecord fileRecord = new FileRecord();
                    readAndMapClientInformation(fileRecord, currentLine);
                    readAndMapProductInformation(fileRecord, currentLine);
                    readAndMapTransactionInformation(fileRecord, currentLine);
                    result.add(fileRecord);
                    result1.add(mapFileRecordToReportModel(fileRecord));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //result.forEach(System.out::println);
        log.info("Reading Input file :: Finished");
        return result1;
    }

    /**
     * @param fileRecord
     * @param currentLine
     */
    private void readAndMapClientInformation(final FileRecord fileRecord, final String currentLine) {

        fileRecord.setClientType(currentLine.substring(3, 7).trim());
        fileRecord.setClientNumber(currentLine.substring(7, 11).trim());
        fileRecord.setAccountNumber(currentLine.substring(11, 15).trim());
        fileRecord.setSubAccountNumber(currentLine.substring(15, 19).trim());

    }

    /**
     * @param fileRecord
     * @param currentLine
     */
    private void readAndMapProductInformation(final FileRecord fileRecord, final String currentLine) {

        fileRecord.setProductGroupCode(currentLine.substring(25, 27).trim());
        fileRecord.setExchangeCode(currentLine.substring(27, 31).trim());
        fileRecord.setSymbol(currentLine.substring(31, 37).trim());
        fileRecord.setExpirationDate(currentLine.substring(37, 45).trim());

    }

    /**
     * @param fileRecord
     * @param currentLine
     */
    private void readAndMapTransactionInformation(final FileRecord fileRecord, final String currentLine) {
        fileRecord.setQuantityLong(new BigDecimal(currentLine.substring(52, 62).trim()));
        fileRecord.setGetQuantityShort(new BigDecimal(currentLine.substring(63, 73).trim()));
    }

    /**
     * @param fileRecord
     * @return
     */
    private ReportModel mapFileRecordToReportModel(final FileRecord fileRecord) {
        ReportModel reportModel = ReportModel.builder()
                .clientInformation(fileRecord.getClientType() + fileRecord.getClientNumber() + fileRecord.getAccountNumber() + fileRecord.getSymbol())
                .productInformation(fileRecord.getExchangeCode() + fileRecord.getProductGroupCode() + fileRecord.getSymbol() + fileRecord.getExpirationDate())
                .transactionAmount(fileRecord.getQuantityLong().add(fileRecord.getGetQuantityShort()))
                .build();

        return reportModel;
    }
}
