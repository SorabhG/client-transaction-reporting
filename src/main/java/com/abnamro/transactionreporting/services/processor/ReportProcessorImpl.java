package com.abnamro.transactionreporting.services.processor;


import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * This class is responsible for formatting the report (grouping and summing fields)
 */
@Slf4j
@Component
public class ReportProcessorImpl implements ReportProcessor {

    public Logger getLogger() {
        return log;
    }

    /**
     * @param reportFeilds {@link ReportModel}
     * @return
     */
    @Override
    public List<ReportModel> formatReport(List<ReportModel> reportFeilds) {
        getLogger().info("Report Formatting :: Started");
        List<ReportModel> formattedReport = reportFeilds.stream().collect(
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
                .flatMap(entry1 -> entry1.getValue()
                        .entrySet()
                        .stream()
                        .map(entry2 -> new ReportModel(entry1.getKey(), entry2.getKey(), entry2.getValue())))
                .collect(Collectors.toList());
        getLogger().info("Report Formatting :: Finished");
        return formattedReport;
    }

}
