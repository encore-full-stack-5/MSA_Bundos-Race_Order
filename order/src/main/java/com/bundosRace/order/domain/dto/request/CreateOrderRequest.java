package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        Long id,
        String name,
        String description,
        List<String> images,
        Integer price,
        Integer discountRate,
        Integer deliveryPrice,
        List<CreateOptionGroupRequest> optionGroups,
        Integer amount,
        String createdAt
) {
}
