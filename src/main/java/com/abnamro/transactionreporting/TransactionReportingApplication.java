package com.abnamro.transactionreporting;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.processor.ReportProcessor;
import com.abnamro.transactionreporting.services.processor.ReportProcessorImpl;
import com.abnamro.transactionreporting.services.reader.FileReader;
import com.abnamro.transactionreporting.services.reader.FileReaderImpl;
import com.abnamro.transactionreporting.services.writer.CSVWriterImpl;
import com.abnamro.transactionreporting.services.writer.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.List;

import static com.abnamro.transactionreporting.constant.TransactionReportingConstants.CSV_OUTPUT_FILE;

@SpringBootApplication
@Slf4j
@EnableScheduling
public class TransactionReportingApplication {

	/**
	 *
	 * @param args {@link String}
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		SpringApplication.run(TransactionReportingApplication.class, args);


	}

}
