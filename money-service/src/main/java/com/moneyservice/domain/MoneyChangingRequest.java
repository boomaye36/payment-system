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
public class MoneyChangingRequest {
    /**
     * The baseline balance of the account. This was the balance of the account before the first
     * activity in the activityWindow.
     */
    @Getter private final String moneyChangingRequestId;
    // 어떤 고객의 증액/감액 요청을 요청했는지 멤버 정보
    @Getter private final String targetMembershipId;
    // 그 요청이 증액 / 감액 요청인지
    @Enumerated(EnumType.STRING)
    @Getter private final int changingType; // 0 : 증액, 1 : 감액
//    enum ChangingType{
//        INCREASING, // 증액
//        DECREASING // 감액
//    }
    // 증액 또는 감액 요청의 금액
    @Getter private final int changingMoneyAmount;
    // 머니 변액 요청에 대한 상태
    @Enumerated(EnumType.STRING)
    @Getter private final int changingMoneyStatus; // 0: 요청, 1: 성공, 2: 실패

//    enum ChangingMoneyStatus {
//        REQUESTED,
//        SUCCEEDED,
//        FAILED,
//        CANCELLED
//    }

    @Getter private final String uuid;
    @Getter private final Date createdAt;

    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId,
           TargetMembershipId targetMembershipId,
            MoneyType changingType,
            ChangingMoneyAmount changingMoneyAmount,
            MoneyStatus status,
            Uuid uuid

            ){
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMembershipId.getTargetMembershipId(),
                changingType.getChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
                status.getChangingMoneyStatus(),
                uuid.getUuid(),
                new Date()
        );
    }

    @Value
    public static class MoneyChangingRequestId {
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
        String moneyChangingRequestId ;
    }

    @Value
    public static class TargetMembershipId {
        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }
        String targetMembershipId ;
    }

    @Value
    public static class MoneyType {
        public MoneyType(int value) {
            this.changingType = value;
        }

        int changingType;
    }
    @Value
    public static class ChangingMoneyAmount {
        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }
        int changingMoneyAmount;
    }




    @Value
    public static class Uuid {
        public Uuid(UUID uuid) {
            this.uuid = uuid.toString();
        }
        String uuid;
    }

    @Value
    public static class MoneyStatus {
        public MoneyStatus(int value) {
            this.changingMoneyStatus = value;
        }
        int changingMoneyStatus;
    }
}