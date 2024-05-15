package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Seller;


import java.time.LocalDateTime;

public record CreateSellerRequest(
        Long id,
        String name
) {
}
