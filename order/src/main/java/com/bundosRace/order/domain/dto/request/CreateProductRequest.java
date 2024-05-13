package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.OptionGroup;
import com.bundosRace.order.domain.entity.Product;

import java.util.List;

public record CreateProductRequest(
        String name,
        Long price,
        int amount,
        List<CreateOptionGroupRequest> optionGroups
) {
    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .amount(amount)
                .build();
    }
}
