package com.bundosRace.order.service;

import com.bundosRace.order.domain.dto.request.CreateOptionGroupRequest;
import com.bundosRace.order.domain.dto.request.CreateProductRequest;
import com.bundosRace.order.domain.entity.Option;
import com.bundosRace.order.domain.entity.OptionGroup;
import com.bundosRace.order.domain.entity.Product;
import com.bundosRace.order.domain.repository.OptionGroupRepository;
import com.bundosRace.order.domain.repository.OptionRepository;
import com.bundosRace.order.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final OptionGroupRepository optionGroupRepository;
    private final OptionRepository optionRepository;

    @Override
    @Transactional
    public void createProduct(CreateProductRequest req) {
        Product product = req.toEntity();
        addOptionGroupsInProduct(req, product);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public Product ggetProduct(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        Product product = byId.orElseThrow(IllegalArgumentException::new);
        return product;
    }

    private void addOptionGroupsInProduct(CreateProductRequest request, Product product) {
        request.optionGroups().forEach((optionGroupRequest) -> {
            OptionGroup optionGroup = optionGroupRequest.toEntity();
            addOptionsInOptionGroup(optionGroupRequest, optionGroup);
            product.addOptionGroup(optionGroup);
        });
    }

    private void addOptionsInOptionGroup(CreateOptionGroupRequest optionGroupRequest, OptionGroup optionGroup) {
        optionGroupRequest.options().forEach((optionRequest) -> {
            Option option = optionRequest.toEntity();
            option.setOptionGroup(optionGroup);
            optionGroup.addOption(option);
        });
    }
}
