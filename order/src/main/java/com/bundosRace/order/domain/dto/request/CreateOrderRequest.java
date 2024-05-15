package com.bundosRace.order.domain.dto.request;

import com.bundosRace.order.domain.entity.Option;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.domain.entity.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(

        // product
        Long productId,
        String name,
        List<String> images,

        // seller
        Long sellerId,
        String sellerName,

        // optionGroup
        List<CreateOptionRequest> options,

        // order
        Integer totalPrice,
        Integer amount,
        String createdAt,
        String deliveryMemo
) {
    public Order orderDTO(UUID userId) {
       return Order.builder()
               .totalPrice(totalPrice)
               .orderAmount(amount)
               .createAt(LocalDate.now())
               .deliveryMemo(deliveryMemo)
               .userId(userId)
               .build();
   }

   public Seller seller() {
        return Seller.builder()
                .id(sellerId)
                .sellerName(sellerName)
                .build();
   }

   public Product product(UUID userId) {
       return Product.builder()
               .id(productId)
               .productName(name)
               .images(images)
               .seller(seller())
               .order(orderDTO(userId))
               .build();
   }

   public List<Option> option(Product product) {
        return this.options.stream().map(o ->
                Option.builder()
                        .id(o.optionId())
                        .optionGroupId(o.optionGroupId())
                        .optionGroupName(o.optionGroupName())
                        .name(o.optionName())
                        .amount(o.amount())
                        .product(product)
                        .build()).toList();
   }
}
