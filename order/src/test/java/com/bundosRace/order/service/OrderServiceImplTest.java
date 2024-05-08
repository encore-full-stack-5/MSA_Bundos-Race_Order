package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOrderDTO;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @Test
    void getAllOrder() {
        // given
        CreateOrderDTO create = new CreateOrderDTO(28000L, 2, "asdasdasd", LocalDate.now());
        orderService.createOrder(create);

        // when, then
        List<ReadOrderDTO> result = orderService.getAllOrder();
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void getAllOrderFail() {
        //given
        CreateOrderDTO create = new CreateOrderDTO(28000L, 2, "asdasdasd", LocalDate.now());
        orderService.createOrder(create);

        // when, then
        List<ReadOrderDTO> result = orderService.getAllOrder();
        Assertions.assertNotNull(result);
    }

    @Test
    void getOrder() {
        // when
        CreateOrderDTO create = new CreateOrderDTO(28000L, 2, "asdasdasd", LocalDate.now());
        Order order = orderRepository.save(create.toEntity());

        // when, then
        ReadOrderDTO result = orderService.getOrder(order.getId());
        Assertions.assertEquals(order.getId(), result.id());
        Assertions.assertNotEquals(0, result.id());
    }

    @Test
    void getOrderFail() {
        // when
        Long orderId = 99L;
        CreateOrderDTO create = new CreateOrderDTO(28000L, 2, "asdasdasd", LocalDate.now());
        orderRepository.save(create.toEntity());

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrder(orderId);
        });
    }

    @Test
    void updateOrder() {
        // given
        Long id = 1L;
        String deliveryMemo = "qweqweqwe";
        CreateOrderDTO create = new CreateOrderDTO(28000L, 2, "asdasdasd", LocalDate.now());
        Order order = orderRepository.save(create.toEntity());

        // when
        UpdateOrderDTO updateOrder = new UpdateOrderDTO(deliveryMemo);
        orderService.updateOrder(id, updateOrder);

        Optional<Order> updatedOrder = orderRepository.findById(order.getId());

        //then
        Assertions.assertTrue(updatedOrder.isPresent());
        Assertions.assertEquals(deliveryMemo, updatedOrder.get().getDeliveryMemo());
    }

}