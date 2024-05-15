package com.bundosRace.order.domain.dto.response;

import com.bundosRace.order.domain.entity.Option;
import com.bundosRace.order.domain.entity.Product;

import java.util.List;

public record ProductOptionDTO(
        Long id,
        String optionName,
        int amount
) {
    public static List<ProductOptionDTO> productResponses(List<Option> options) {
        return options.stream().map(option -> new ProductOptionDTO(
                option.getId(),
                option.getName(),
                option.getAmount()
        )).toList();
    }
}
