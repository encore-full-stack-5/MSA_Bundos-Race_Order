package com.bundosRace.order.domain.repository;

import com.bundosRace.order.domain.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
