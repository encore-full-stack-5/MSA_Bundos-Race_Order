package com.bundosRace.order.domain.entity;

import com.bundosRace.order.config.utils.JsonStringListConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor @Getter
@Builder
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "images", columnDefinition = "json")
    @Convert(converter = JsonStringListConverter.class)
    private List<String> images;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Column(name = "OPTION_ID")
    private List<Option> options;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Order order;
}
