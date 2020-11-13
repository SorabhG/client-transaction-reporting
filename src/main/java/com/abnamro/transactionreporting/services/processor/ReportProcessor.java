package com.abnamro.transactionreporting.services.processor;

import com.abnamro.transactionreporting.model.dataObjects.ReportModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Contract for compiling and formatting the report.
 */
@Service
public interface ReportProcessor {
    /**
     *
     * @param reportFeilds {@link List<ReportModel>}
     * @return
     */
    List<ReportModel> formatReport(List<ReportModel> reportFeilds);
}
