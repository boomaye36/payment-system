package com.moneyservice.adapter.in.web;

import com.moneyservice.domain.MoneyChangingRequest;
import org.springframework.stereotype.Component;

@Component
public class MoneyChangingResultDetailMapper {
    public MoneyChangingResultDetail mapToMoneyChangingResultResponse(MoneyChangingRequest moneyChangingRequest){
        return new MoneyChangingResultDetail(
                moneyChangingRequest.getMoneyChangingRequestId(),
                moneyChangingRequest.getChangingType(),
                moneyChangingRequest.getChangingMoneyStatus(),
                moneyChangingRequest.getChangingMoneyAmount(),
                moneyChangingRequest.
        )
    }
}
