package com.bankingservice.application.service;

import com.bankingservice.adapter.out.external.bank.BankAccount;
import com.bankingservice.adapter.out.external.bank.ExternalFirmbankingRequest;
import com.bankingservice.adapter.out.external.bank.FirmbankingResult;
import com.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.bankingservice.adapter.out.persistence.RequestFirmbankingJpaEntity;
import com.bankingservice.adapter.out.persistence.RequestFirmbankingMapper;
import com.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.bankingservice.application.port.in.ReqeustFirmbankingCommand;
import com.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.bankingservice.application.port.out.RegisterBankAccountPort;
import com.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.bankingservice.application.port.out.RequestFirmbankingPort;
import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
@Service
public class RequestFirmbankingService implements RequestFirmbankingUseCase {
    private final RequestFirmbankingPort requestFirmbankingPort;
    private final RequestFirmbankingMapper mapper;
    private final RequestExternalFirmbankingPort requestExternalFirmbankingPort;

    @Override
    public FirmbankingRequest requestFrimbanking(ReqeustFirmbankingCommand command) {

        // Business Logic

        // a -> b 계좌

        // 1. 요청에 대해 정보를 먼저 write. "요청" 상태로
        RequestFirmbankingJpaEntity requestFirmbankingJpaEntity = requestFirmbankingPort.createFirmbankingRequest(
               new FirmbankingRequest.FromBankName(command.getFromBankName()),
                new FirmbankingRequest.FromBankAcountNumber(command.getFromBankAccountNumber()),
                new FirmbankingRequest.ToBankName(command.getToBankName()),
                new FirmbankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmbankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmbankingRequest.FirmbankingStatus(0)
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmbankingResult result = requestExternalFirmbankingPort.requestExternalFirmbanking(new ExternalFirmbankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));
        // Transactional UUID
        UUID randUUID = UUID.randomUUID();
        requestFirmbankingJpaEntity.setUuid(randUUID);
        // 3. 결과에 따라서 1번에서 작성했던 FirmbankingRequest 정보를 Update
        if(result.getResultCode() == 0){
            requestFirmbankingJpaEntity.setFirmbankingstatus(1); // 성공
        }
        else requestFirmbankingJpaEntity.setFirmbankingstatus(2); // 실패
        // 4. 결과 리턴
       return mapper.mapToDomainEntity(requestFirmbankingPort.modifybankingRequest(requestFirmbankingJpaEntity),
               randUUID);
    }
}
