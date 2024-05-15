package com.bundosRace.order.domain.dto.response;


import com.bundosRace.order.domain.entity.Option;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.domain.entity.Seller;

import java.util.List;

public record ProductResponse(
        Long id,
        String productName,
        List<String> images,
        List<ProductOptionDTO> productOptions,

        // seller
        String sellerName
) {
    public static List<ProductResponse> productResponses(List<Product> products) {
        return products.stream().map(product -> {
            List<ProductOptionDTO> productOptionDTOList = ProductOptionDTO.productResponses(product.getOptions());
            return new ProductResponse(
                    product.getId(),
                    product.getProductName(),
                    product.getImages(),
                    productOptionDTOList,
                    product.getSeller().getSellerName()
            );
        }).toList();
    }

}