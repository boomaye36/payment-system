package com.membershipservice.application.service;

import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.adapter.out.persistence.MembershipMapper;
import com.membershipservice.application.port.in.FindMembershipCommand;
import com.membershipservice.application.port.in.FindMembershipUseCase;
import com.membershipservice.application.port.out.FindMembershipPort;
import com.membershipservice.common.UseCase;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Service
public class FindMembershipService implements FindMembershipUseCase {
    private final FindMembershipPort findMembershipPort;
    private final MembershipMapper membershipMapper;
    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(new Membership.MembershipId(command.getMembershipId()));

        return membershipMapper.mapToDomainEntity(entity);
    }
}
