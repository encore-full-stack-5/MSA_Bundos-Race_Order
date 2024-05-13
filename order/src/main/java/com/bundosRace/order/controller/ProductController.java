package com.bundosRace.order.controller;

import com.bundosRace.order.domain.dto.request.CreateProductRequest;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getAll(@PathVariable("id") Long id) {
        return productService.ggetProduct(id);
    }

    @PostMapping("/create")
    public void createProduct(@RequestBody CreateProductRequest req) {
        productService.createProduct(req);
    }
}
