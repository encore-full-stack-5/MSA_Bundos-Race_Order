package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Order;

import java.time.LocalDate;

public record CreateOrderDTO(
        long totalPrice,
        int orderAmount,
        String deliveryMemo,
        LocalDate createAt
) {
    public Order toEntity() {
        return Order.builder()
                .totalPrice(totalPrice)
                .orderAmount(orderAmount)
                .deliveryMemo(deliveryMemo)
                .createAt(createAt)
                .build();
    }
}
