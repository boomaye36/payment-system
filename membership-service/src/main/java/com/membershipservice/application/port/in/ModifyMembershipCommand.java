package com.membershipservice.application.port.in;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<ModifyMembershipCommand> {
    @NotNull
    private final String membershipId;
    @NotNull
    private final String name;
    @NotNull

    private final String email;
    @NotNull
    @NotBlank
    private final String address;

    private boolean isCorp;
    @AssertTrue
    private boolean isValid;


}
