package com.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
    @Query("SELECT e FROM MemberMoneyJpaEntity e WHERE e.memberId = :membershipId")
    Optional<MemberMoneyJpaEntity> findMemberMoneyJpaEntityByMemberId(@Param("membershipId") String memberId);
}
