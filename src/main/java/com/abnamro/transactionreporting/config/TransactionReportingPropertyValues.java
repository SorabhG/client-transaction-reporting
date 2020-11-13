package com.abnamro.transactionreporting.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class TransactionReportingPropertyValues {

    @Value("${reportingService.clientMode:false}")
    private boolean clientMode;
}
