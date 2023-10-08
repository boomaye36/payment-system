package com.moneyservice.application.port.in;

import com.common.SelfValidating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class IncreasemoneyRequestCommand extends SelfValidating<IncreasemoneyRequestCommand> {
    @NotNull
    private final String targetMembershipId;
    @NotNull

    private final int amount;


}
