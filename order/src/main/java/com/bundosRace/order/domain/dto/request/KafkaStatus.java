package com.bundosRace.order.domain.dto.request;

public record KafkaStatus<T>(
        T data, String status
) {
}
