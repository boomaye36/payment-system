package com.moneyservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyChangingRequestRepository extends JpaRepository<MoneyChangingRequestJpaEntity, Long> {
}
