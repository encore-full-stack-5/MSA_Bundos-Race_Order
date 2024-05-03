package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Order;

public record CreateOrderDTO(
        String address,
        long totalPrice,
        int orderAmount,
        String requestList
) {
    public Order toEntity() {
        return Order.builder()
                .address(address)
                .totalPrice(totalPrice)
                .orderAmount(orderAmount)
                .requestList(requestList)
                .build();
    }
}
