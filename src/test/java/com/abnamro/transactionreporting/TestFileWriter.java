package com.abnamro.transactionreporting;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.writer.CSVWriterImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestFileWriter {

    @InjectMocks
    CSVWriterImpl csvWriter;

    private List<ReportModel> items = null;
    @Before
    public void setup(){
        items = Arrays.asList(
                new ReportModel("CL43210003N1", "CMEFUN120100910", new BigDecimal(70)),
                new ReportModel("CL12340003N1", "CMEFUN120100910", new BigDecimal(700)),
                new ReportModel("CL43210002NK", "SGXFUNK20100910", new BigDecimal(500))
        );
    }

    @Test
    public void TestFileWriter() throws IOException {
        csvWriter.writeCSV("TestOutput.csv",items);
        
    }

}
