package com.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity, Long> {
}
