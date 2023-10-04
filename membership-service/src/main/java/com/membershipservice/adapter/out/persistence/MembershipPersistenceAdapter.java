package com.membershipservice.adapter.out.persistence;

import com.membershipservice.application.port.out.FindMembershipPort;
import com.membershipservice.application.port.out.ModifyMembershipPort;
import com.membershipservice.application.port.out.RegisterMembershipPort;
import com.membershipservice.common.PersistenceAdapter;
import com.membershipservice.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {
    @Autowired
    private SpringDataMembershipRepository springDataMembershipRepository;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return springDataMembershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getNameValue(),
                        membershipEmail.getEmailValue(),
                        membershipAddress.getAddressValue(),
                        membershipIsValid.isValidValue(),
                        membershipIsCorp.isCorp()
                )
        );
    }

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        return springDataMembershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));

    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity membershipJpaEntity =
                springDataMembershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
        membershipJpaEntity.setName(membershipName.getNameValue());
        membershipJpaEntity.setAddress(membershipAddress.getAddressValue());
        membershipJpaEntity.setEmail(membershipEmail.getEmailValue());
        membershipJpaEntity.setCorp(membershipIsCorp.isCorp());
        membershipJpaEntity.setValid(membershipJpaEntity.isValid());

        return springDataMembershipRepository.save(membershipJpaEntity);

    }

}
