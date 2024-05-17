package com.bundosRace.order.domain.dto.request;

public record KafkaRequest(
        Long productId,
        Boolean reviewCheck
) {
}
