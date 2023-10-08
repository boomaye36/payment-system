package com.moneyservice.adapter.out.persistence;

import com.moneyservice.domain.MoneyChangingRequest;
import com.moneyservice.domain.RegisteredBankAccount;
import org.springframework.stereotype.Component;
@Component

public class MoneyChangingRequestMapper {
    public MoneyChangingRequest mapToDomainEntity(MoneyChangingRequestJpaEntity moneyChangingRequestJpaEntity){
        return MoneyChangingRequest.generateMoneyChangingRequest(
                new MoneyChangingRequest.MoneyChangingRequestId(moneyChangingRequestJpaEntity.getMoneyChangingRequestId()+""),
                new MoneyChangingRequest.TargetMembershipId(moneyChangingRequestJpaEntity.getTargetMembershipId()),
                new MoneyChangingRequest.MoneyType(moneyChangingRequestJpaEntity.getMoneyChangingType()),
                new MoneyChangingRequest.ChangingMoneyAmount(moneyChangingRequestJpaEntity.getMoneyAmount()),
                new MoneyChangingRequest.MoneyStatus(moneyChangingRequestJpaEntity.getChangingMoneyStatus()),
                new MoneyChangingRequest.Uuid(moneyChangingRequestJpaEntity.getUuid())
        );
    }
}
