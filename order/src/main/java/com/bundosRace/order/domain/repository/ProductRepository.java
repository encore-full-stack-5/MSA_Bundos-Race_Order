package com.bundosRace.order.domain.repository;

import com.bundosRace.order.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
