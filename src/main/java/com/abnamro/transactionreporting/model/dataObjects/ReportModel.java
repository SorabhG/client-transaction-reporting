package com.abnamro.transactionreporting.model.dataObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@Builder
public class ReportModel {
    private String clientInformation;
    private String productInformation;
    private BigDecimal transactionAmount;
}
