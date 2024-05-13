package com.bundosRace.order.config.api;

import com.bundosRace.order.domain.dto.request.SellProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class ApiProduct {
    private final FeignProduct feignProduct;

    @Async
    public void updateSellProduct(SellProductsRequest sellProductsRequest){
        try {
            feignProduct.updateSellProductOrder(sellProductsRequest);
        }catch (Exception e){
            ResponseEntity.noContent();
        }
    }

    @Async
    public Object getProductsByUserId(long productId) {
        return feignProduct.getProductsByUserId(productId);
    }
}
