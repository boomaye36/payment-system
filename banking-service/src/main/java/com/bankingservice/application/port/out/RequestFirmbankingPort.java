package com.bankingservice.application.port.out;


import com.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.bankingservice.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.bankingservice.adapter.out.persistence.RequestFirmbankingMapper;
import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;

public interface RequestFirmbankingPort {

    RequestFirmbankingJpaEntity createFirmbankingRequest(
            FirmbankingRequest.FromBankName fromBankName,
            FirmbankingRequest.FromBankAcountNumber fromBankAcountNumber,
            FirmbankingRequest.ToBankName toBankName,
            FirmbankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmbankingRequest.MoneyAmount moneyAmount,
            FirmbankingRequest.FirmbankingStatus firmbankingStatus
    );

    RequestFirmbankingJpaEntity modifybankingRequest(
            RequestFirmbankingJpaEntity entity
    );
}
