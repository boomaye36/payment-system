package com.moneyservice.adapter.in.web;

import com.common.WebAdapter;
import com.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.moneyservice.application.port.in.IncreasemoneyRequestCommand;
import com.moneyservice.domain.MoneyChangingRequest;
import com.moneyservice.domain.RegisteredBankAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@WebAdapter
public class RequestMoneyChangingController {
    private final IncreaseMoneyRequestUseCase increaseMoneyRequestUseCase;

    @PostMapping("/money/increase")
    MoneyChangingResultDetail IncreaseMoneyChangingRequest(@RequestBody IncreaseMoneyChangingRequest request) {
        // request

        // request -> Command
        IncreasemoneyRequestCommand command = IncreasemoneyRequestCommand.builder()
                .targetMembershipId(request.getTargetMembershipId())
                .amount(request.getAmount())
                .build();

        MoneyChangingRequest moneyChangingRequest = increaseMoneyRequestUseCase.increaseMoneyRequest(command);
        MoneyChangingResultDetail resultDetail = new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                0, // 증액
                0, // 성공
                moneyChangingRequest.getChangingMoneyAmount()
        );
        // Usecase ~~(request)
        return resultDetail;

    }
}
