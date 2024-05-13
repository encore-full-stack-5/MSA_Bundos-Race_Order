package com.bundosRace.order.domain.repository;

import com.bundosRace.order.domain.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
