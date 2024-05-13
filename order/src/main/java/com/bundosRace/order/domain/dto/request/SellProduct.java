package com.bundosRace.order.domain.dto.request;

import java.util.List;
import java.util.Map;

public record SellProduct(
        Long productId,
        Integer count,
        List<Long> optionsId
) {
}
