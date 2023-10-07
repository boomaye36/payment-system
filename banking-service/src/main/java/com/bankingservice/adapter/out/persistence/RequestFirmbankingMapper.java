package com.bankingservice.adapter.out.persistence;

import com.bankingservice.adapter.in.web.RequestFirmbankingRequest;
import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component

public class RequestFirmbankingMapper {
    public FirmbankingRequest mapToDomainEntity(RequestFirmbankingJpaEntity requestFirmbankingJpaEntity, UUID uuid){
        return FirmbankingRequest.generatedFirmbankingRequest(
                new FirmbankingRequest.FirmbankingRequestId(requestFirmbankingJpaEntity.getRequestFirmbankingId() + ""),
                new FirmbankingRequest.FromBankName(requestFirmbankingJpaEntity.getFromBankName()),
                new FirmbankingRequest.FromBankAcountNumber(requestFirmbankingJpaEntity.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(requestFirmbankingJpaEntity.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(requestFirmbankingJpaEntity.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(requestFirmbankingJpaEntity.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(requestFirmbankingJpaEntity.getFirmbankingstatus()),
                uuid
        );
    }
}
