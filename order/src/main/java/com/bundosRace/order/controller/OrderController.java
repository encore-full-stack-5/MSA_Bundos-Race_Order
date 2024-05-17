package com.bundosRace.order.controller;

import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.UpdateOrderDTO;
import com.bundosRace.order.domain.dto.response.ReadOrderDTO;
import com.bundosRace.order.domain.entity.User;
import com.bundosRace.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

//    @PostMapping("/user")
//    public String token(
//            @RequestBody User user
//    ) {
////        User user = new User(UUID.fromString("00000000-0000-0000-0000-" +
////                "000000000001"), "asd", "1234");
//        String token = jwtTokenUtils.generateToken(user);
//
//        return token;
//    }


//    @GetMapping("user/order/{id}")
//    public ReadOrderDTO findOneOrderFromUser(
//            @PathVariable("id") Long id,
//            @RequestHeader("Authorization") String userInfoToken
//    ) {
//        String token = userInfoToken.substring(7);
//        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
//        return orderService.getOneOrderFromUser(tokenInfo, id);
//    }
    @GetMapping("/user/order")
    public List<ReadOrderDTO> getAllOrder(
            @RequestParam("token") String token
    ) {
        return orderService.getAllOrderToUser(token);
    }

    @PostMapping("/create")
    public void saveOrder(
            @RequestBody CreateOrderRequest req,
            @RequestParam("token") String token
    ) {
        orderService.createOrder(token, req);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(
            @PathVariable("id") Long id,
            @RequestBody UpdateOrderDTO req,
            @RequestParam("token") String token
    ) {
        orderService.updateOrder(token, id, req);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrder(
            @PathVariable("id") Long id,
            @RequestParam("token") String token
    ) {
        orderService.deleteOrder(token, id);
    }
}
