package com.bundosRace.order.service;

import com.bundosRace.order.config.utils.TokenInfo;
import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.KafkaRequest;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.dto.request.KafkaStatus;

import java.util.List;

public interface OrderService {
    void createOrder(TokenInfo tokenInfo, CreateOrderRequest req);
    List<ReadOrderDTO> getAllOrderToUser(TokenInfo tokenInfo);
    void updateOrder(TokenInfo tokenInfo, Long id, UpdateOrderDTO req);
    void deleteOrder(TokenInfo tokenInfo, Long id);
    void listenReview(KafkaStatus<KafkaRequest> status);
}
