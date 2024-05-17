package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.KafkaRequest;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.dto.request.KafkaStatus;

import java.util.List;

public interface OrderService {
    void createOrder(String token, CreateOrderRequest req);
    List<ReadOrderDTO> getAllOrderToUser(String token);
    void updateOrder(String token, Long id, UpdateOrderDTO req);
    void deleteOrder(String token, Long id);
    void listenReview(KafkaRequest status);
}
