package com.moneyservice.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}
