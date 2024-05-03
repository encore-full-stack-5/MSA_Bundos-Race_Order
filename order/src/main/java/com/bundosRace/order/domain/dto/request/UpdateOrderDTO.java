package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Order;

public record UpdateOrderDTO(
        String requestList, long totalPrice, int orderAmount
) {
    public Order from() {
        return Order.builder()
                .requestList(requestList)
                .build();
    }
}
