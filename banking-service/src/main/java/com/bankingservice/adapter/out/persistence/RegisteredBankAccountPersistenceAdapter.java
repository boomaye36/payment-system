package com.bankingservice.adapter.out.persistence;

import com.bankingservice.application.port.out.RegisterBankAccountPort;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.PersistenceAdapter;
import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.application.port.out.RegisterMembershipPort;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class RegisteredBankAccountPersistenceAdapter implements RegisterBankAccountPort {

    private final RegisteredBankAccountRepository repository;


    @Override
    public RegisteredBankAccountJpaEntity createRegisteredBankAccount(RegisteredBankAccount.MembershipId membershipId, RegisteredBankAccount.RegisteredBankAccountName bankAccountName, RegisteredBankAccount.BankAccountNumber bankAccountNumber, RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid) {
        return repository.save(
                new RegisteredBankAccountJpaEntity(
                        membershipId.getMembershipId() ,
                        bankAccountName.getBankName(),
                        bankAccountNumber.getBankAccountNumber(),
                        linkedStatusIsValid.isLinkedStatusIsValid()
                )
        );
    }
}
