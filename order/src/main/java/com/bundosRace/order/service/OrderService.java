package com.bundosRace.order.service;

import com.bundosRace.order.config.utils.TokenInfo;
import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.SellProductsRequest;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;

import java.util.List;

public interface OrderService {
    int productTotalPrice(CreateOrderRequest req);
    SellProductsRequest sellProductsRequests(CreateOrderRequest req);
    void createOrder(TokenInfo tokenInfo, CreateOrderRequest req);
    List<ReadOrderDTO> getAllOrderToUser(TokenInfo tokenInfo);
    ReadOrderDTO getOneOrderFromUser(TokenInfo tokenInfo, Long id);
    void updateOrder(TokenInfo tokenInfo, Long id, UpdateOrderDTO req);
    void deleteOrder(TokenInfo tokenInfo, Long id);
}
