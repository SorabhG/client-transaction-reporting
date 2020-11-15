package com.abnamro.transactionreporting;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


public class TestProcessorLogic {

    List<ReportModel> items = null;

    @BeforeEach
    public void setup() {
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
    public void testClientInformationGrouping() {

        Map<String, Map<String, List<ReportModel>>> collect1 = items.stream().collect(
                groupingBy(ReportModel::getClientInformation, groupingBy(ReportModel::getProductInformation))
        );


        List<ReportModel> collect = items.stream().collect(
                groupingBy(
                        ReportModel::getClientInformation,
                        groupingBy(
                                ReportModel::getProductInformation,
                                Collectors.reducing(
                                        BigDecimal.ZERO,
                                        ReportModel::getTransactionAmount,
                                        BigDecimal::add)
                        )
                )
        ).entrySet()
                .stream()
                .flatMap(e1 -> e1.getValue()
                        .entrySet()
                        .stream()
                        .map(e2 -> new ReportModel(e1.getKey(), e2.getKey(), e2.getValue())))
                .collect(Collectors.toList());
        System.out.println(collect);
        Assert.assertNotNull(collect);


    }
}
