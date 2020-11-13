package com.abnamro.transactionreporting.model.dataObjects;

import lombok.Data;

@Data
public class ProductInformation {
    private String exchangeCode;
    private String productGroupCode;
    private String symbol;
    private String expirationDate;
}
