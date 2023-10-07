package com.bankingservice.application.port.out;


import com.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.bankingservice.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.RegisteredBankAccountName bankAccountName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid
    );
}
