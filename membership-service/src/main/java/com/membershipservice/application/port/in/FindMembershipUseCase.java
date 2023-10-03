package com.membershipservice.application.port.in;

import com.membershipservice.domain.Membership;

public interface FindMembershipUseCase {
    Membership findMembership(FindMembershipCommand command);
}
