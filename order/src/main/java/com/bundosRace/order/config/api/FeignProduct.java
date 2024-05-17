package com.bundosRace.order.config.api;

import com.bundosRace.order.domain.dto.request.SellProductsRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("PRODUCT-SERVICE")
public interface FeignProduct {

    @PatchMapping("/api/v1/products/sell")
    ResponseEntity<String> updateSellProductOrder(
            @RequestBody SellProductsRequest sellProductsRequest
    );
}
