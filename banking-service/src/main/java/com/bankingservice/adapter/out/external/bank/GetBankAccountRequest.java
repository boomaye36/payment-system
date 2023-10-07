package com.bankingservice.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class GetBankAccountRequest {
    private String bankName;

    private String bankAccountNumber;


}
