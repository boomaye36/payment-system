package com.bankingservice.adapter.in.web;

import com.bankingservice.application.port.in.RegisterBankAccountCommand;
import com.bankingservice.application.port.in.RegisterBankAccountUseCase;
import com.bankingservice.domain.RegisteredBankAccount;
import com.common.WebAdapter;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RegisterBankAccountController {
    private final RegisterBankAccountUseCase registerBankAccountUseCase;
    @PostMapping("/membership/register")
    RegisteredBankAccount registeredBankAccount(@RequestBody RegisterBankAccountRequest request){
        // request

        // request -> Command
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();
        // Usecase ~~(request)
        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);
        if(registeredBankAccount == null) {
            return null;
        }
        return registerBankAccountUseCase.registerBankAccount(command);

    }
}
