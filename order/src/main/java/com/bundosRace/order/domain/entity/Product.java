package com.bundosRace.order.domain.entity;

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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_TITLE")
    private String name;

//    @Column(name = "images", columnDefinition = "jsonb")
//    @Convert(converter = JsonStringListConverter.class)
//    private List<String> images;

    @Column(name = "PRODUCT_PRICE")
    private Long price;

    @Column(name = "PRODUCT_AMOUNT")
    private int amount;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionGroup> optionGroups = new ArrayList<>();

    public void addOptionGroup(OptionGroup optionGroup) {
        this.optionGroups.add(optionGroup);
        optionGroup.setProduct(this);
    }
}
