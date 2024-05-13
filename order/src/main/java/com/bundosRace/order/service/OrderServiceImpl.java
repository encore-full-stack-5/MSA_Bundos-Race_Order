package com.bundosRace.order.service;

import com.bundosRace.order.config.api.ApiProduct;
import com.bundosRace.order.config.utils.TokenInfo;
import com.bundosRace.order.domain.dto.request.*;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ApiProduct apiProduct;


    @Override
    @Transactional()
    public synchronized void createOrder(TokenInfo tokenInfo, CreateOrderRequest req) {
        List<Order> order = orderRepository.findAllByUserId(tokenInfo.id());
        if(!order.isEmpty() || req == null)
            throw new IllegalArgumentException();

        int totalPrice = productTotalPrice(req);

        SellProductsRequest sellProductsRequest = sellProductsRequests(req);
        apiProduct.updateSellProduct(sellProductsRequest);

        orderRepository.save(Order.builder()
                        .totalPrice(totalPrice)
                        .orderAmount(req.amount())
                        .createAt(LocalDate.now())
                        .userId(tokenInfo.id())
                        .productId(req.id())
                        .build()
        );
    }

    public SellProductsRequest sellProductsRequests(CreateOrderRequest req) {
        List<SellProduct> sellProducts = new ArrayList<>();
        List<Long> optionsId = new ArrayList<>();
        req.optionGroups().forEach(optionGroup -> {
            optionGroup.options().forEach(option -> {
                optionsId.add(option.id());
            });
        });
        SellProduct products = new SellProduct(req.id(), req.amount(), optionsId);
        sellProducts.add(products);
        return new SellProductsRequest(sellProducts);
    }

    public int productTotalPrice(CreateOrderRequest req) {
        AtomicInteger totalPrice = new AtomicInteger();
        req.optionGroups().forEach(optionRequest -> {
            optionRequest.options().forEach(option -> {
                totalPrice.addAndGet(option.price());
            });
        });
        totalPrice.addAndGet(req.price());

        return Integer.parseInt(String.valueOf(totalPrice));
    }

    @Override
    public List<ReadOrderDTO> getAllOrderToUser(TokenInfo tokenInfo) {
        List<Order> allOrderToUser = orderRepository.findAllByUserId(tokenInfo.id());
        if(tokenInfo.id() == null || allOrderToUser.isEmpty()) throw new IllegalArgumentException();
        allOrderToUser.forEach(productInfo -> {
            apiProduct.getProductsByUserId(productInfo.getProductId());
        });

        return allOrderToUser.stream()
                .map(ReadOrderDTO::getOrder)
                .toList();
    }

    @Override
    public ReadOrderDTO getOneOrderFromUser(TokenInfo tokenInfo, Long id) {
        if(tokenInfo.id() == null) throw new IllegalArgumentException();
        Optional<Order> exist = orderRepository.findById(id);
        Order order = exist.orElseThrow(IllegalArgumentException::new);
        return ReadOrderDTO.getOrder(order);
    }

    @Override
    public void updateOrder(TokenInfo tokenInfo, Long id, UpdateOrderDTO req) {
        Optional<Order> byIdAndUserId = orderRepository.findByIdAndUserId(id, tokenInfo.id());
        Order order = byIdAndUserId.orElseThrow(IllegalArgumentException::new);

        orderRepository.save(req.update(order));
    }

    @Override
    public void deleteOrder(TokenInfo tokenInfo, Long id) {
        if(tokenInfo == null) throw new IllegalArgumentException();
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow(IllegalArgumentException::new);
        orderRepository.delete(order);
    }
}
