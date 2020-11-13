package com.abnamro.transactionreporting.scheduler;

import com.abnamro.transactionreporting.main.ReportingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
/**
 * Scheduler to trigger reporting.
 */
public class ClientReportScheduler {

    private final ReportingService reportingService;

    /**
     * Constuctor Intialization.
     * @param reportingService
     */
    public ClientReportScheduler(ReportingService reportingService) {
        this.reportingService = reportingService;
    }


    @Scheduled(cron = "${reportingService.job.interval:0 */1 * ? * *}")
    // @Scheduled(cron = "${reportingService.job.interval:0 0 6 * * ?}")  --every day 6 am.
    public void startReporting() throws IOException {
        log.info("Scheduler started");
        this.reportingService.process();
        log.info("Scheduler Finished");
    }

}
