package com.bundosRace.order.config.api;

import com.bundosRace.order.domain.dto.request.SellProductsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("PRODUCT-SERVICE")
public interface FeignProduct {

    @PatchMapping("/api/v1/products/sell/{id}")
    void updateSellProductOrder(
            @RequestBody SellProductsRequest sellProductsRequest
    );
}
