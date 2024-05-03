package com.bundosRace.order.controller;

import com.bundosRace.order.domain.dto.request.CreateOrderDTO;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @PostMapping("/create")
    public void saveOrder(@RequestBody CreateOrderDTO req) {
        orderService.createOrder(req);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(@PathVariable("id") Long id, @RequestBody UpdateOrderDTO req) {
        orderService.updateOrder(id, req);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
    }
}
