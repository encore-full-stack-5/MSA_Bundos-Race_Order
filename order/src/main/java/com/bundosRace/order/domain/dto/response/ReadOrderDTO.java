package com.bundosRace.order.domain.dto.response;

import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.Product;

import java.time.LocalDate;
import java.util.List;

public record ReadOrderDTO(
        // order
        Long orderId,
        Integer totalPrice,
        int orderAmount,
        LocalDate createAt,
        String deliveryMemo,
//        String sellerName,
//        Long productId,
//        String productName,
//        List<String> images,
        List<ProductResponse> products

        // product
//        List<ProductOptionDTO> productOption
) {
    public static ReadOrderDTO readOrderDTO(Order order) {
//        List<ProductOptionDTO> options = product.getOptions().stream().map(option -> new ProductOptionDTO(
//                option.getId(),
//                option.getName(),
//                option.getAmount()
//        )).toList();

        List<ProductResponse> productResponses = ProductResponse.productResponses(order.getProducts());
        return new ReadOrderDTO(
                order.getId(),
                order.getTotalPrice(),
                order.getOrderAmount(),
                order.getCreateAt(),
                order.getDeliveryMemo(),
                productResponses
//                product.getSeller().getSellerName(),
//                product.getId(),
//                product.getProductName(),
//                product.getImages(),
//                options
        );
    }
}
