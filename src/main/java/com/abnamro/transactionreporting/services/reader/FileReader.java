package com.abnamro.transactionreporting.services.reader;



import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.io.IOException;
import java.util.List;

/*
 * Contract for reading a file.
 */
@Service
public interface FileReader {
    /**
     *
     * @return {@link ReportModel}
     * @throws IOException
     */
    List<ReportModel> readFlatFile() throws IOException;
}
