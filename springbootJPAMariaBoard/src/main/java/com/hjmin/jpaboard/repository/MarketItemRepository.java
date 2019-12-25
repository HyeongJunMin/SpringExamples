package com.hjmin.jpaboard.repository;

import com.hjmin.jpaboard.domain.MarketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketItemRepository extends JpaRepository<MarketItem, Long> {
}
