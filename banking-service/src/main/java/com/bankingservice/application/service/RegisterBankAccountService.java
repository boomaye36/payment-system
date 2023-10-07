package com.bankingservice.application.service;

import com.bankingservice.adapter.out.external.bank.BankAccount;
import com.bankingservice.adapter.out.external.bank.GetBankAccountRequest;
import com.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.bankingservice.adapter.out.persistence.RegisteredBankAccountMapper;
import com.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.bankingservice.application.port.out.RegisterBankAccountPort;
import com.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.UseCase;
import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.adapter.out.persistence.MembershipMapper;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.membershipservice.application.port.out.RegisterMembershipPort;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
@Service
public class RegisterBankAccountService implements RegisterBankAccountUseCase {
    private final RegisterBankAccountPort registerBankAccountPort;
    private final RegisteredBankAccountMapper mapper;
    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {

        // 은행 계좌를 등록해야 하는 서비스 (비즈니스 로직)

        // 1. 등록된 계좌인지 확인한다.

        // 외부의 은행에 이 계좌 정상인지? 확인을 해야함.
        // business logic -> External System
        // port -> Adapter -> External System
        // Port

        // 실제 외부 계좌 정보 get
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
                new GetBankAccountRequest(command.getBankName(),
                command.getBankAccountNumber()));
        boolean isValid = accountInfo.isValid();
        // 2. 등록가능한 계좌라면 등록한다. 성공하면 등록에 성공한 등록 정보를 리텅
        // 2-1. 등록가능하지 않은 계좌라면 예외를 리턴
        if(isValid){
            // 등록 정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo =  registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId() + ""),
                    new RegisteredBankAccount.RegisteredBankAccountName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.LinkedStatusIsValid(command.isValid())
            );
            return mapper.mapToDomainEntity(savedAccountInfo);
        }
        return null;
    }
}
