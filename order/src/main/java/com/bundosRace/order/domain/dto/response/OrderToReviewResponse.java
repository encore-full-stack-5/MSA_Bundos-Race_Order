package com.bundosRace.order.domain.dto.response;

import com.bundosRace.order.domain.entity.Option;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.Product;

import java.time.LocalDate;
import java.util.List;

public record OrderToReviewResponse(
        Long orderId,
        LocalDate createAt,
        String productName,
        String sellerName,
        List<Option> options
) {
    public static OrderToReviewResponse fromReview(Order order, Product product, List<Option> options1) {
        return new OrderToReviewResponse(
                order.getId(),
                order.getCreateAt(),
                product.getProductName(),
                product.getSeller().getSellerName(),
                options1.stream().toList()
        );
    }
}
