package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOrderDTO;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.entity.Order;
import com.bundosRace.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    @Override
    public void createOrder(CreateOrderDTO req) {
        if(req == null) {
            throw new IllegalArgumentException();
        }
        orderRepository.save(req.toEntity());
    }

    @Override
    public List<ReadOrderDTO> getAllOrder() {
        return orderRepository.findAll().stream()
                .map(ReadOrderDTO::getOrder)
                .toList();
    }

    @Override
    public ReadOrderDTO getOrder(Long id) {
        Optional<Order> exist = orderRepository.findById(id);
        Order order = exist.orElseThrow(IllegalArgumentException::new);
        return ReadOrderDTO.getOrder(order);
    }

    @Override
    public void updateOrder(Long id, UpdateOrderDTO req) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow(IllegalArgumentException::new);
        orderRepository.save(req.from());
    }

    @Override
    public void deleteOrder(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.orElseThrow(IllegalArgumentException::new);
        orderRepository.delete(order);
    }
}
