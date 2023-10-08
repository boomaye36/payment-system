package com.moneyservice.adapter.out.persistence;

import com.common.PersistenceAdapter;
import com.moneyservice.application.port.out.IncreaseMoneyPort;
import com.moneyservice.domain.MemberMoney;
import com.moneyservice.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class MoneyChangingRequestPersistenceAdapter implements IncreaseMoneyPort {

    private final MoneyChangingRequestRepository repository;
    private final MemberMoneyRepository memberMoneyRepository;


    @Override
    public MoneyChangingRequestJpaEntity createMoneyChangingRequest(MoneyChangingRequest.TargetMembershipId targetMembershipId, MoneyChangingRequest.MoneyType moneyType, MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount, MoneyChangingRequest.MoneyStatus moneyStatus, MoneyChangingRequest.Uuid uuid) {
        return repository.save(
                new MoneyChangingRequestJpaEntity(
                        targetMembershipId.getTargetMembershipId(),
                        moneyType.getChangingType(),
                        changingMoneyAmount.getChangingMoneyAmount(),
                        new Timestamp(System.currentTimeMillis()),
                        moneyStatus.getChangingMoneyStatus(),
                        UUID.randomUUID()
                )
        );
    }

    @Override
    public MemberMoneyJpaEntity increaseMoney(MemberMoney.MembershipId membershipId, int increaseMoneyAmount) {
        MemberMoneyJpaEntity entity;
        try{
             entity = memberMoneyRepository.getById(Long.parseLong(membershipId.getMembershipId()));

        }catch (Exception e){
            entity = new MemberMoneyJpaEntity(
                    membershipId.getMembershipId(),
                    increaseMoneyAmount
            );
            return memberMoneyRepository.save(entity);
        }

        entity.setBalance(entity.getBalance() + increaseMoneyAmount);
        return memberMoneyRepository.save(entity);
    }
}
