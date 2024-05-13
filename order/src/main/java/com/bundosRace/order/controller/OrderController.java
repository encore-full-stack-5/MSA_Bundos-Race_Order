package com.bundosRace.order.controller;

import com.bundosRace.order.config.utils.JwtTokenUtils;
import com.bundosRace.order.config.utils.TokenInfo;
import com.bundosRace.order.domain.dto.request.CreateOrderRequest;
import com.bundosRace.order.domain.dto.request.CreateProductRequest;
import com.bundosRace.order.domain.dto.request.SellProductsRequest;
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
    private final JwtTokenUtils jwtTokenUtils;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @PostMapping("/price")
    public int price(@RequestBody CreateOrderRequest req) {
        return orderService.productTotalPrice(req);
    }

    @PostMapping("/asd")
    public SellProductsRequest sellProductsRequest(@RequestBody CreateOrderRequest req) {
        return orderService.sellProductsRequests(req);
    }

    @PostMapping("/user")
    public String token(
            @RequestBody User user
    ) {
//        User user = new User(UUID.fromString("00000000-0000-0000-0000-" +
//                "000000000001"), "asd", "1234");
        String token = jwtTokenUtils.generateToken(user);

        return token;
    }

    @GetMapping("/user/order")
    public List<ReadOrderDTO> getAllOrder(
            @RequestHeader("Authorization") String userInfoToken
    ) {
        String token = userInfoToken.substring(4);
        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
        return orderService.getAllOrderToUser(tokenInfo);
    }

    @GetMapping("user/order/{id}")
    public ReadOrderDTO findOneOrderFromUser(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String userInfoToken
    ) {
        String token = userInfoToken.substring(4);
        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
        return orderService.getOneOrderFromUser(tokenInfo, id);
    }

    @PostMapping("/create")
    public void saveOrder(
            @RequestBody CreateOrderRequest req,
            @RequestHeader("Authorization") String userInfoToken
    ) {
        String token = userInfoToken.substring(4);
        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
        orderService.createOrder(tokenInfo, req);
    }

    @PutMapping("/update/{id}")
    public void updateOrder(
            @PathVariable("id") Long id,
            @RequestBody UpdateOrderDTO req,
            @RequestHeader("Authorization") String userInfoToken
    ) {
        String token = userInfoToken.substring(4);
        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
        orderService.updateOrder(tokenInfo, id, req);
    }

    @DeleteMapping("delete/{id}")
    public void deleteOrder(
            @PathVariable("id") Long id,
            @RequestHeader("Authorization") String userInfoToken
    ) {
        String token = userInfoToken.substring(7);
        TokenInfo tokenInfo = jwtTokenUtils.parseInfo(token);
        orderService.deleteOrder(tokenInfo, id);
    }
}
