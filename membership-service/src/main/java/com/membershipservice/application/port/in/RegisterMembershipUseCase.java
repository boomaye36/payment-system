package com.membershipservice.application.port.in;

import com.common.UseCase;
import com.membershipservice.domain.Membership;

@UseCase
public interface RegisterMembershipUseCase {
    Membership registerMembership(RegisterMembershipCommand command);
}
