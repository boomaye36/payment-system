package com.moneyservice.adapter.out.external.bank;

import com.bankingservice.adapter.out.persistence.RegisteredBankAccountRepository;
import com.bankingservice.application.port.out.RequestBankAccountInfoPort;
import com.bankingservice.application.port.out.RequestExternalFirmbankingPort;
import com.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmbankingPort {


    private final RegisteredBankAccountRepository registeredBankAccountRepository;
    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부 은행에 http 을 통해서
        // 실제 은행 계좌 정보를 가져오고
        // 실제 은행 계좌 -> BankAccount
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmbankingResult requestExternalFirmbanking(ExternalFirmbankingRequest request) {

        // 실제로 외부 은행에 http 통신을 통해서
        // 펌뱅킹 요청을 하고

        //그 결과를 외부 은행의 실제 결과를 -> FirmbankingResult 파싱
        return new FirmbankingResult(0);
    }
}
