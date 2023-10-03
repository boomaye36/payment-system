package com.membershipservice.application.service;

import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import com.membershipservice.application.port.in.RegisterMembershipCommand;
import com.membershipservice.application.port.in.RegisterMembershipUseCase;
import com.membershipservice.adapter.out.persistence.MembershipMapper;
import com.membershipservice.application.port.out.RegisterMembershipPort;
import com.membershipservice.common.UseCase;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@UseCase
@Service
public class RegisterMembershipService implements RegisterMembershipUseCase {
    private final RegisterMembershipPort registerMembershipPort;
    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        // command -> DB

        // business logic -> DB

        // external System

        // port, adapter
        MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
               new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress (command.getAddress()),
                new Membership.MembershipIsValid (command.isValid()),
                new Membership.MembershipIsCorp (command.isCorp())
        );

        // entity -> Membership

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
