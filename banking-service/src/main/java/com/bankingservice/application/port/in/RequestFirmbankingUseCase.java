package com.bankingservice.application.port.in;

import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.UseCase;

@UseCase
public interface RequestFirmbankingUseCase {
    FirmbankingRequest requestFrimbanking(ReqeustFirmbankingCommand command);
}
