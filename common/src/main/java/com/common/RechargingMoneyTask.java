package com.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RechargingMoneyTask { // increase money
    private String taskId;
    private String taskName;
    private String membershipId;
    private List<SubTask> subTaskList;
    private String toBankName;
    private String toBankAccountNumber;
    private int moneyAmount;

}
