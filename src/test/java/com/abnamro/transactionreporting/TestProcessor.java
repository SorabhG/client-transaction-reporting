package com.abnamro.transactionreporting;


import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import com.abnamro.transactionreporting.services.processor.ReportProcessorImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestProcessor {


   @MockBean
    ReportProcessorImpl reportProcessor;

    private List<ReportModel> items = null;

   @BeforeEach
    public void setup(){
       items = Arrays.asList(
               new ReportModel("CL123400020001", "SGXFUNK20100910", new BigDecimal(5)),
               new ReportModel("CL123400020001", "SGXFUNK20100910", new BigDecimal(3)),
               new ReportModel("CL123400020001", "SGXFUNK20100910", new BigDecimal(5)),
               new ReportModel("CL123400030001", "CMEFUN120100910", new BigDecimal(2)),
               new ReportModel("CL123400030001", "CMEFUN120100910", new BigDecimal(2)),
               new ReportModel("CL123400030001", "CMEFUN120100910", new BigDecimal(1)),
               new ReportModel("CL123400030001", "DMEFUN120100910", new BigDecimal(2)),
               new ReportModel("CL123400030001", "DMEFUN120100910", new BigDecimal(4)),
               new ReportModel("CL123400030001", "DMEFUN120100910", new BigDecimal(3))
       );
   }

   @Test
    public void testReportFormatting(){

       List<ReportModel> reportModels = reportProcessor.formatReport(items);
       Assert.assertNotNull(reportModels);

   }

}
