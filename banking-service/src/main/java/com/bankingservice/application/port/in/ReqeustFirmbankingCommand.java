package com.bankingservice.application.port.in;

import com.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ReqeustFirmbankingCommand extends SelfValidating<ReqeustFirmbankingCommand> {
    @NotNull
    private final String fromBankName;
    @NotNull

    private final String fromBankAccountNumber;
    @NotNull
    @NotBlank
    private final String toBankName;
    @NotNull

    private final String toBankAccountNumber;
    @NotNull
    private final int moneyAmount;

}
