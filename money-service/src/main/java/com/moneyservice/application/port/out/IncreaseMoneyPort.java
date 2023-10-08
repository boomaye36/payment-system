package com.moneyservice.application.port.out;


import com.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;
import com.moneyservice.adapter.out.persistence.MoneyChangingRequestJpaEntity;
import com.moneyservice.domain.MemberMoney;
import com.moneyservice.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {

    MoneyChangingRequestJpaEntity createMoneyChangingRequest(
            MoneyChangingRequest.TargetMembershipId targetMembershipId,
            MoneyChangingRequest.MoneyType moneyType,
            MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingRequest.MoneyStatus moneyStatus,
            MoneyChangingRequest.Uuid uuid
    );

    MemberMoneyJpaEntity increaseMoney(
            MemberMoney.MembershipId membershipId,
            int increaseMoneyAmount
    );
}
