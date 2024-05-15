package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Product;

import java.util.List;

public record CreateProductRequest(
        Long productId,
        String name,
        List<String> images
) {
}
