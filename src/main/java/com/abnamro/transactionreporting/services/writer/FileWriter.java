package com.abnamro.transactionreporting.services.writer;


import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Contract for Writing the CSV file.
 */
@Service
public interface FileWriter {
    void writeCSV(final String csvName, final List<ReportModel> reportModels) throws IOException;
}
