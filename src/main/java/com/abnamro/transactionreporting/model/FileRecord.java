package com.abnamro.transactionreporting.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FileRecord {

    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expirationDate;
    private BigDecimal quantityLong;
    private BigDecimal getQuantityShort;
}
