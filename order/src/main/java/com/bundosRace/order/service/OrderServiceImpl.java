package com.bundosRace.order.service;

import com.bundosRace.order.config.api.ApiProduct;
import com.bundosRace.order.config.utils.TokenInfo;
import com.bundosRace.order.domain.dto.request.*;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.dto.request.KafkaStatus;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.domain.repository.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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


    @Override
    @Transactional()
    @ResponseStatus(HttpStatus.CREATED)
    public synchronized void createOrder(TokenInfo tokenInfo, CreateOrderRequest req) {
        if(tokenInfo.id() == null || req == null)
            throw new IllegalArgumentException();

        orderRepository.save(req.orderDTO(tokenInfo.id()));
        sellerRepository.save(req.seller());
        Product product = productRepository.save(req.product(tokenInfo.id()));
        optionRepository.saveAll(req.option(product));
    }

    @Override
    public List<ReadOrderDTO> getAllOrderToUser(TokenInfo tokenInfo) {

        List<Order> orderFromUser = orderRepository.findAllByUserId(tokenInfo.id());

        if(tokenInfo.id() == null || orderFromUser.isEmpty()) throw new IllegalArgumentException();

        return orderFromUser.stream().map(ReadOrderDTO::readOrderDTO
        ).collect(Collectors.toList());
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

    @Override
    public void listenReview(KafkaStatus<KafkaRequest> status) {
        List<Order> orders = orderRepository.findAllByProductsId(status.data().productId());
        orders.stream().map(order -> {
            if(!order.getReviewCheck())
                order.setReviewCheck(status.data().check());
            return Response.ok("성공!");
        });
    }
}
