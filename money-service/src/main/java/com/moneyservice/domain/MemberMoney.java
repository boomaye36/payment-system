package com.moneyservice.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberMoney {

    @Getter private final String memberMoneyId;
    // 어떤 고객의 증액/감액 요청을 요청했는지 멤버 정보
    @Getter private final String membershipId;
    @Getter private final int balance;



    public static MemberMoney generateMemberMoney(
            MemberMoneyId memberMoneyId,
            MembershipId membershipId,
            Balance balance

            ){
        return new MemberMoney(
                memberMoneyId.getMemberMoneyId(),
                membershipId.getMembershipId(),
                balance.getBalance()
        );
    }

    @Value
    public static class MemberMoneyId {
        public MemberMoneyId(String value) {
            this.memberMoneyId = value;
        }
        String memberMoneyId ;
    }

    @Value
    public static class MembershipId {
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId ;
    }

    @Value
    public static class Balance {
        public Balance(int value) {
            this.balance = value;
        }

        int balance;
    }
}