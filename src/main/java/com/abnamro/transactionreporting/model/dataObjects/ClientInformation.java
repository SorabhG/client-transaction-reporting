package com.abnamro.transactionreporting.model.dataObjects;

import lombok.Data;

@Data
public class ClientInformation {
    private String clientType;
    private String clientNumber;
    private String accountNumber;
    private String subAccountNumber;
}
