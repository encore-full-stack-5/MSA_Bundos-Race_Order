package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.entity.User;
import com.bundosRace.order.domain.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    private final JwtTokenUtils jwtTokenUtils = new JwtTokenUtils(1000*60L, "fjni2kasj24kjvfkjgjk535kjjkgkjb3kknvnknvnsnasd");
    private final User user = new User(UUID.fromString("00000000-0000-0000-0000-000000000001"), "asd", "1234");
    private final String token = jwtTokenUtils.generateToken(user);
    private final TokenInfo tokenInfo = jwtTokenUtils.parseinfo(token);

    @Test
    void getAllOrderToUser() {
        // given
        CreateOrderRequest create = new CreateOrderRequest(28000L, 2, "asdasdasd", 1L);
        orderService.createOrder(tokenInfo, create);
        List<Order> orders = orderRepository.findAllByUserId(tokenInfo.id());

        // when, then
        List<ReadOrderDTO> result = orderService.getAllOrderToUser(tokenInfo);
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getAllOrderToUserFail() {
        //given
        CreateOrderRequest create = new CreateOrderRequest(28000L, 2, "asdasdasd", 1L);
        orderService.createOrder(tokenInfo, create);

        // when
        List<ReadOrderDTO> result = orderService.getAllOrderToUser(tokenInfo);

        //then
        Assertions.assertNotNull(result);
    }

    @Test
    void getOneOrderFromUser() {
        // when
        CreateOrderRequest create = new CreateOrderRequest(28000L, 2, "asdasdasd", 1L);
        Order order = orderRepository.save(create.toEntity(tokenInfo.id()));

        // when
        ReadOrderDTO result = orderService.getOneOrderFromUser(tokenInfo, order.getId());

        // then
        Assertions.assertEquals(order.getId(), result.id());
        Assertions.assertNotEquals(0, result.id());
    }

    @Test
    void getOneOrderFromUserFail() {
        // when
        Long orderId = 99L;
        CreateOrderRequest create = new CreateOrderRequest(28000L, 2, "asdasdasd", 1L);
        orderRepository.save(create.toEntity(tokenInfo.id()));

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOneOrderFromUser(tokenInfo, orderId);
        });
    }

    @Test
    void updateOrder() {
        // given
        Long id = 1L;
        String deliveryMemo = "qweqweqwe";
        CreateOrderRequest create = new CreateOrderRequest(28000L, 2, "asdasdasd", 1L);
        Order order = orderRepository.save(create.toEntity(tokenInfo.id()));

        // when
        UpdateOrderDTO updateOrder = new UpdateOrderDTO(deliveryMemo);
        orderService.updateOrder(tokenInfo, id, updateOrder);

        Optional<Order> updatedOrder = orderRepository.findById(order.getId());

        //then
        Assertions.assertTrue(updatedOrder.isPresent());
        Assertions.assertEquals(deliveryMemo, updatedOrder.get().getDeliveryMemo());
        Assertions.assertEquals(id, updatedOrder.get().getId());
    }

}