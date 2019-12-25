package com.hjmin.jpaboard.repository;

import com.hjmin.jpaboard.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
}
