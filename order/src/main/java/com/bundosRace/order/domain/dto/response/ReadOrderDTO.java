package com.bundosRace.order.domain.dto.response;

import com.bundosRace.order.domain.entity.Order;

import java.time.LocalDate;

public record ReadOrderDTO(
        Long id, long totalPrice, int orderAmount,
        LocalDate createAt, String deliveryMemo
) {
    public static ReadOrderDTO getOrder(Order order) {
        return new ReadOrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderAmount(),
                order.getCreateAt(),
                order.getDeliveryMemo()
        );
    }
}
