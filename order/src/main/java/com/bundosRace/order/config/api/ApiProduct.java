package com.bundosRace.order.config.api;


import com.bundosRace.order.domain.dto.request.SellProductsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class ApiProduct {
    private final FeignProduct feignProduct;

    public void updateSellProduct(SellProductsRequest sellProductsRequest){
        try {
            ResponseEntity<String> response = feignProduct.updateSellProductOrder(sellProductsRequest);
            System.out.println(response.getBody());
        }catch (Exception e){
            System.out.println(e.getMessage());
            ResponseEntity.noContent();
        }
    }
}
