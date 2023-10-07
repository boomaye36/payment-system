package com.bankingservice.adapter.in.web;

import com.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.bankingservice.application.port.in.ReqeustFirmbankingCommand;
import com.bankingservice.application.port.in.RequestFirmbankingUseCase;
import com.bankingservice.domain.FirmbankingRequest;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RequestFrimbankingController {
    private final RequestFirmbankingUseCase requestFirmbankingUseCase;
    @PostMapping("/banking/firmbanking/request")
    FirmbankingRequest registeredBankAccount(@RequestBody RequestFirmbankingRequest request){
        // request

        // request -> Command
        ReqeustFirmbankingCommand command = ReqeustFirmbankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .toBankName(request.getToBankName())
                .moneyAmount(request.getMoneyAmount())
                .build();
        // Usecase ~~(request)

        return requestFirmbankingUseCase.requestFrimbanking(command);

    }
}
