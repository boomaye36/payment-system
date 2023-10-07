package com.membershipservice.application.port.in;

import com.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterMembershipCommand extends SelfValidating<RegisterMembershipCommand> {
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

    public RegisterMembershipCommand(String name, String email, String address, boolean isCorp, boolean isValid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.isCorp = isCorp;
        this.isValid = isValid;
        this.validateSelf(); // null check -> exception

    }
}
