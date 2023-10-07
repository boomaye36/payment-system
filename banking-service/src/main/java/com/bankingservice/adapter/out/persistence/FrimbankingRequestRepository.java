package com.bankingservice.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FrimbankingRequestRepository extends JpaRepository<RequestFirmbankingJpaEntity, Long> {
}
