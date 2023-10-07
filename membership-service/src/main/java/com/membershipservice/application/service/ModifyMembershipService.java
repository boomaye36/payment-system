package com.membershipservice.application.service;

import com.common.UseCase;
import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.adapter.out.persistence.MembershipMapper;
import com.membershipservice.application.port.in.FindMembershipCommand;
import com.membershipservice.application.port.in.FindMembershipUseCase;
import com.membershipservice.application.port.in.ModifyMembershipCommand;
import com.membershipservice.application.port.in.ModifyMembershipUseCase;
import com.membershipservice.application.port.out.FindMembershipPort;
import com.membershipservice.application.port.out.ModifyMembershipPort;
import com.membershipservice.application.port.out.RegisterMembershipPort;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
@Service
public class ModifyMembershipService implements ModifyMembershipUseCase {
    private final ModifyMembershipPort modifyMembershipPort;
    private final MembershipMapper membershipMapper;


    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress (command.getAddress()),
                new Membership.MembershipIsValid (command.isValid()),
                new Membership.MembershipIsCorp (command.isCorp())
        );

        // entity -> Membership

        return membershipMapper.mapToDomainEntity(jpaEntity);    }
}
