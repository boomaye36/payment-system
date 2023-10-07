package com.bankingservice.adapter.out.external.bank;

import com.bankingservice.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.bankingservice.adapter.out.persistence.RegisteredBankAccountRepository;
import com.bankingservice.application.port.out.RegisterBankAccountPort;
import com.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.ExternalSystemAdapter;
import com.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort {


    private final RegisteredBankAccountRepository registeredBankAccountRepository;
    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부 은행에 http 을 통해서
        // 실제 은행 계좌 정보를 가져오고
        // 실제 은행 계좌 -> BankAccount
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
