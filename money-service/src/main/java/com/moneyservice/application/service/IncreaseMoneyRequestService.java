package com.moneyservice.application.service;

import com.common.UseCase;
import com.moneyservice.adapter.out.persistence.MemberMoneyJpaEntity;
import com.moneyservice.adapter.out.persistence.MoneyChangingRequestMapper;
import com.moneyservice.application.port.in.IncreaseMoneyRequestUseCase;
import com.moneyservice.application.port.in.IncreasemoneyRequestCommand;
import com.moneyservice.application.port.out.IncreaseMoneyPort;
import com.moneyservice.domain.MemberMoney;
import com.moneyservice.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Transactional
@UseCase
@Service
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final MoneyChangingRequestMapper mapper;
    private final IncreaseMoneyPort increaseMoneyPort;
    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreasemoneyRequestCommand command) {
        // 돈의 충전, 증액이라는 과정

        // 1. 고객 정보가 정상인지 확인

        // 2. 고객의 연동된 계좌가 있는지, 그리고 고객의 연동된 계좌의 잔액이 충분한지 확인

        // 3. 법인 계좌 상태도 정상확인

        // 4. 증액을 위한 "기록". 요층 상태로 MoneyChangingRequest를 생성한다.

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 법인 계좌)

        // 6-1. 결과가 정상적이라면 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴

        // 성공 시에 멤버의 MemberMoney 값 증액 필요

        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()) ,command.getAmount());
        if(memberMoneyJpaEntity != null){
            return  mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.MoneyType(1), // 증액
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.MoneyStatus(1), // 성공
                    new MoneyChangingRequest.Uuid(UUID.randomUUID())

            ));
        }
        return null;
        // 6-2. 결과가 실패라면 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴

    }
}
