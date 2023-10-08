package com.moneyservice.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;
    // 증액, 감액
    private int moneyChangingType;
    private int moneyChangingResultStatus;
    private int amount;
}

//enum MoneyChangingType{
//    INCREASE,
//    DECREASE
//}
//enum MoneyChangingResultStatus{
//    SUCCEEDED,
//    FAILED,
//    FAILED_NOT_ENOUGH_MONEY,
//    FAILED_NOT_EXIST_MEMBERSHIP,
//    FAILED_NOT_EXIST_CHANGING_REQUEST
//}