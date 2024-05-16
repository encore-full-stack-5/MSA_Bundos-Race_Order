package com.bundosRace.order.config.api;

import com.bundosRace.order.domain.dto.request.SellProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ApiProduct {
    private final FeignProduct feignProduct;

    @Async
    public void updateSellProduct(List<SellProduct> sellProductsRequest){
        try {
            feignProduct.updateSellProductOrder(sellProductsRequest);
        }catch (Exception e){
            ResponseEntity.noContent();
        }
    }
}
