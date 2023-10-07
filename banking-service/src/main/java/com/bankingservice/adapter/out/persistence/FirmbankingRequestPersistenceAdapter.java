package com.bankingservice.adapter.out.persistence;

import com.bankingservice.application.port.out.RegisterBankAccountPort;
import com.bankingservice.application.port.out.RequestFirmbankingPort;
import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class FirmbankingRequestPersistenceAdapter implements RequestFirmbankingPort {

    private final FrimbankingRequestRepository repository;


    @Override
    public RequestFirmbankingJpaEntity createFirmbankingRequest(FirmbankingRequest.FromBankName fromBankName, FirmbankingRequest.FromBankAcountNumber fromBankAcountNumber, FirmbankingRequest.ToBankName toBankName, FirmbankingRequest.ToBankAccountNumber toBankAccountNumber, FirmbankingRequest.MoneyAmount moneyAmount, FirmbankingRequest.FirmbankingStatus firmbankingStatus) {
        return repository.save(new RequestFirmbankingJpaEntity(
                fromBankName.getFromBankName(),
                fromBankName.getFromBankName(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(),
                firmbankingStatus.getFirmbankingStatus(),
                UUID.randomUUID()
        ));
    }

    @Override
    public RequestFirmbankingJpaEntity modifybankingRequest(RequestFirmbankingJpaEntity entity) {
        return repository.save(entity);
    }
}
