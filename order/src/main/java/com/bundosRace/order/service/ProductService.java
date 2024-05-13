package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateProductRequest;
import com.bundosRace.order.domain.entity.Product;

import java.util.List;

public interface ProductService {
    void createProduct(CreateProductRequest req);
    Product ggetProduct(Long id);
}
