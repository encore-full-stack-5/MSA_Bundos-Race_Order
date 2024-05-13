package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Option;

public record CreateOptionRequest(
        Long id,
        String name,
        Integer price,
        int amount
) {
    public Option toEntity() {
        return Option.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .build();
    }
}
