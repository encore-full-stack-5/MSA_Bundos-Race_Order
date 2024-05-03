package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOrderDTO;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;

import java.util.List;

public interface OrderService {
    void createOrder(CreateOrderDTO req);
    List<ReadOrderDTO> getAllOrder();
    ReadOrderDTO getOrder(Long id);
    void updateOrder(Long id, UpdateOrderDTO req);
    void deleteOrder(Long id);
}
