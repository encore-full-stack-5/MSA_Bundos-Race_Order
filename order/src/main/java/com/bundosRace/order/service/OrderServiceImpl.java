package com.bundosRace.order.service;

import com.bundosRace.order.config.api.ApiAuth;
import com.bundosRace.order.config.api.ApiProduct;
import com.bundosRace.order.domain.dto.request.*;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.dto.request.KafkaStatus;
import com.bundosRace.order.domain.dto.response.TokenInfoResponse;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.domain.repository.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final SellerRepository sellerRepository;
    private final ApiProduct apiProduct;
    private final ApiAuth apiAuth;


    @Override
    @Transactional()
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(String token, CreateOrderRequest req) {
        TokenInfoResponse user = apiAuth.parseToken(token);
        if(user.id() == null || req == null)
            throw new IllegalArgumentException();

        sellProduct sellProduct1 = new sellProduct(req.productId(), req.amount(), readOptionId(req.options()));
        SellProductsRequest sellProductsRequest = sellProducts(sellProduct1);

        System.out.println(sellProductsRequest.toString());

        apiProduct.updateSellProduct(sellProductsRequest);

        orderRepository.save(req.orderDTO(user.id()));
        sellerRepository.save(req.seller());
        Product product = productRepository.save(req.product(user.id()));
        optionRepository.saveAll(req.option(product));
    }

    public List<Long> readOptionId(List<CreateOptionRequest> options) {
        List<Long> optionIds = new ArrayList<>();
        options.forEach(option -> optionIds.add(option.optionId()));
        return optionIds;
    }

    public SellProductsRequest sellProducts(sellProduct sellProduct) {
        List<sellProduct> sellProductList = new ArrayList<>();
        sellProductList.add(sellProduct);
        return new SellProductsRequest(sellProductList);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<ReadOrderDTO> getAllOrderToUser(String token) {
        TokenInfoResponse user = apiAuth.parseToken(token);
        List<Order> orderFromUser = orderRepository.findAllByUserId(user.id());
        System.out.println(orderFromUser.get(0).getReviewCheck());

        if(user.id() == null || orderFromUser.isEmpty()) throw new IllegalArgumentException();

        return orderFromUser.stream().map(ReadOrderDTO::readOrderDTO
        ).collect(Collectors.toList());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void updateOrder(String token, Long id, UpdateOrderDTO req) {
        TokenInfoResponse user = apiAuth.parseToken(token);
        Optional<Order> byIdAndUserId = orderRepository.findByIdAndUserId(id, user.id());
        Order order = byIdAndUserId.orElseThrow(IllegalArgumentException::new);

        orderRepository.save(req.update(order));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(String token, Long id) {
        TokenInfoResponse user = apiAuth.parseToken(token);
        if(user == null) throw new IllegalArgumentException();
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow(IllegalArgumentException::new);
        orderRepository.delete(order);
    }

    @Override
    @KafkaListener(topics = "review-topic")
    @Transactional
    public void listenReview(KafkaRequest status) {
        System.out.println(status.toString());
        List<Order> orders = orderRepository.findAllByProductsId(status.productId());
        orders.forEach(order -> {
            if(order.getReviewCheck() == null || !order.getReviewCheck())
                order.setReviewCheck(true);
        });
    }
}
