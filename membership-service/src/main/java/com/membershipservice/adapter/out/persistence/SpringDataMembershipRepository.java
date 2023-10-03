package com.membershipservice.adapter.out.persistence;

import com.membershipservice.adapter.out.persistence.MembershipJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMembershipRepository extends JpaRepository<MembershipJpaEntity, Long> {
}
