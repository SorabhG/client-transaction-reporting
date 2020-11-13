package com.abnamro.transactionreporting;

import com.abnamro.transactionreporting.config.TransactionReportingPropertyValues;
import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.reader.FileReaderImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class TestFileReaderService {

    @InjectMocks
    FileReaderImpl fileReader;

    @Mock
    TransactionReportingPropertyValues transactionReportingPropertyValues;

    @Before
    public void setup(){
        when(transactionReportingPropertyValues.isClientMode()).thenReturn(true);
    }

    @Test
    public void testFileReader() throws IOException {
        List<ReportModel> reportModels = fileReader.readFlatFile();
        Assert.assertNotNull(reportModels);
    }
}
