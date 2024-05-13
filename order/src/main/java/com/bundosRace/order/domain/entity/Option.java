package com.bundosRace.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "OPTIONS")
public class Option {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_ID")
    private Long id;

    @Column(name = "OPTION_NAME")
    private String name;

    @Column(name = "OPTION_PRICE")
    private Integer price;

    @Column(name = "OPTION_AMOUNT")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    @JoinColumn(name = "OPTION_GROUP_ID")
    private OptionGroup optionGroup;

    @Setter
    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
