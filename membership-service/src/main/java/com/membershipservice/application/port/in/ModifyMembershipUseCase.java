package com.membershipservice.application.port.in;

import com.membershipservice.domain.Membership;

import java.lang.reflect.Member;

public interface ModifyMembershipUseCase {
    Membership modifyMembership(ModifyMembershipCommand command);
}
