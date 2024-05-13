package com.bundosRace.order.domain.dto.request;

import java.util.List;

public record SellProductsRequest(
        List<SellProduct> sellProducts
) {
}
