package com.abnamro.transactionreporting.services.reader;


import com.abnamro.transactionreporting.config.TransactionReportingPropertyValues;
import com.abnamro.transactionreporting.constant.TransactionReportingConstants;
import com.abnamro.transactionreporting.model.FileRecord;
import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private final TransactionReportingPropertyValues transactionReportingPropertyValues;


    /**
     * Constructor Initialization.
     *
     * @param transactionReportingPropertyValues
     */
    public FileReaderImpl(TransactionReportingPropertyValues transactionReportingPropertyValues) {
        this.transactionReportingPropertyValues = transactionReportingPropertyValues;
    }

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
        final List<FileRecord> fileRecords = new ArrayList<>();
        final List<ReportModel> reportModels = new ArrayList<>();

        log.info("Reading Input file :: Started");

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(this.getClass().getClassLoader()
                            .getResourceAsStream(TransactionReportingConstants.INPUT_FILE)));
            String currentLine;
            log.info("Client mode is [{}]", this.transactionReportingPropertyValues.isClientMode());
            while ((currentLine = br.readLine()) != null) {
                FileRecord fileRecord = new FileRecord();
                if (this.transactionReportingPropertyValues.isClientMode()) {
                    if (CLIENT_NUMBER_1234.equalsIgnoreCase(currentLine.substring(7, 11).trim())) {
                        readFlatFileAndMapsToJavaObject(fileRecords, reportModels, currentLine, fileRecord);
                    }

                } else {
                    readFlatFileAndMapsToJavaObject(fileRecords, reportModels, currentLine, fileRecord);

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fileRecords.forEach(System.out::println);
        log.info("Reading Input file :: Finished");
        return reportModels;
    }

    /**
     * Reads Flat file and maps it to java objects.
     *
     * @param fileRecords  {@Link List<FileRecord>}
     * @param reportModels {@Link List<ReportModel>}
     * @param currentLine  {@Link String}
     * @param fileRecord   {@Link FileRecord}
     */
    private void readFlatFileAndMapsToJavaObject(List<FileRecord> fileRecords, List<ReportModel> reportModels, String currentLine, FileRecord fileRecord) {
        readAndMapClientInformation(fileRecord, currentLine);
        readAndMapProductInformation(fileRecord, currentLine);
        readAndMapTransactionInformation(fileRecord, currentLine);
        fileRecords.add(fileRecord);
        reportModels.add(mapFileRecordToReportModel(fileRecord));
    }

    /**
     * Method to read and map client information.
     *
     * @param fileRecord  {@link FileRecord}
     * @param currentLine {@link String}
     */
    private void readAndMapClientInformation(final FileRecord fileRecord, final String currentLine) {

        fileRecord.setClientType(currentLine.substring(3, 7).trim());
        fileRecord.setClientNumber(currentLine.substring(7, 11).trim());
        fileRecord.setAccountNumber(currentLine.substring(11, 15).trim());
        fileRecord.setSubAccountNumber(currentLine.substring(15, 19).trim());

    }

    /**
     * Method to read and Map production information.
     *
     * @param fileRecord  {@link FileRecord}
     * @param currentLine {@link String}
     */
    private void readAndMapProductInformation(final FileRecord fileRecord, final String currentLine) {

        fileRecord.setProductGroupCode(currentLine.substring(25, 27).trim());
        fileRecord.setExchangeCode(currentLine.substring(27, 31).trim());
        fileRecord.setSymbol(currentLine.substring(31, 37).trim());
        fileRecord.setExpirationDate(currentLine.substring(37, 45).trim());

    }

    /**
     * Method to read and map Transaction information.
     *
     * @param fileRecord  {@link FileRecord}
     * @param currentLine {@link String}
     */
    private void readAndMapTransactionInformation(final FileRecord fileRecord, final String currentLine) {
        fileRecord.setQuantityLong(new BigDecimal(currentLine.substring(52, 62).trim()));
        fileRecord.setGetQuantityShort(new BigDecimal(currentLine.substring(63, 73).trim()));
    }

    /**
     * Maps file record to Reporting Model
     *
     * @param fileRecord {@link FileRecord}
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
