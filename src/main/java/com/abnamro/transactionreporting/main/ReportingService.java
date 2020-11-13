package com.abnamro.transactionreporting.main;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface ReportingService {
    void process() throws IOException;
}
