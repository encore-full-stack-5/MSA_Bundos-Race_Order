package com.bundosRace.order.domain.repository;

import com.bundosRace.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdAndUserId(Long id, UUID userId);
    List<Order> findAllByUserId(UUID userId);
    Optional<Order> findByUserId(UUID userId);

}
