package com.bundosRace.order.domain.dto.request;

import java.util.List;
import java.util.Map;

public record sellProduct(
        Long productId,
        Integer count,
        List<Long> optionIds
) {
}
