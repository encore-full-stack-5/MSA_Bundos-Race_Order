package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Order;

import java.time.LocalDate;

public record UpdateOrderDTO(
        String deliveryMemo
) {
    public Order update(Order order) {
        order.setDeliveryMemo(deliveryMemo);
        order.setUpdateAt(LocalDate.now());
        return order;
    }
}
