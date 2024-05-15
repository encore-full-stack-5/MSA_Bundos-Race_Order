package com.bundosRace.order.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "OPTIONGROUP_ID")
    private Long optionGroupId;

    @Column(name = "OPTIONGROUP_NAME")
    private String optionGroupName;

    @Column(name = "OPTION_NAME")
    private String name;

    @Column(name = "OPTION_AMOUNT")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
