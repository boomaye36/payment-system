package com.membershipservice.application.port.out;

import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.domain.Membership;

public interface FindMembershipPort {
    MembershipJpaEntity findMembership(
            Membership.MembershipId membershipId
    );
}
