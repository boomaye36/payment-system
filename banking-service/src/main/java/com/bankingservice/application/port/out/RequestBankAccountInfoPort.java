package com.bankingservice.application.port.out;

import com.bankingservice.adapter.out.external.bank.BankAccount;
import com.bankingservice.adapter.out.external.bank.GetBankAccountRequest;

public interface RequestBankAccountInfoPort {
     BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
