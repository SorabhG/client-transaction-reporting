package com.abnamro.transactionreporting.main;

import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * COntract of Reporting Service
 */
@Service
public interface ReportingService {
    void process() throws IOException;
}
