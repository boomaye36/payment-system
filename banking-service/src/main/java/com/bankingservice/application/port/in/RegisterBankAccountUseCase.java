package com.bankingservice.application.port.in;

import com.bankingservice.domain.RegisteredBankAccount;
import com.common.UseCase;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.domain.Membership;

@UseCase
public interface RegisterBankAccountUseCase {
    RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command);
}
