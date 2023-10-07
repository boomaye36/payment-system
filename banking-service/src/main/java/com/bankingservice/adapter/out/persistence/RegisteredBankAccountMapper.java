package com.bankingservice.adapter.out.persistence;

import com.bankingservice.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;
@Component

public class RegisteredBankAccountMapper {
    public RegisteredBankAccount mapToDomainEntity(RegisteredBankAccountJpaEntity registeredBankAccountJpaEntity){
        return RegisteredBankAccount.generatedRegisteredBankAccount(
                new RegisteredBankAccount.RegisteredBankAccountId(registeredBankAccountJpaEntity.getRegisteredBankAccountId() + ""),
                new RegisteredBankAccount.MembershipId(registeredBankAccountJpaEntity.getMembershipId() + ""),
                new RegisteredBankAccount.RegisteredBankAccountName(registeredBankAccountJpaEntity.getBankName()),
                new RegisteredBankAccount.BankAccountNumber(registeredBankAccountJpaEntity.getBankAccountNumber()),
                new RegisteredBankAccount.LinkedStatusIsValid(registeredBankAccountJpaEntity.isLinkedStatusIsValid())
        );
    }
}
