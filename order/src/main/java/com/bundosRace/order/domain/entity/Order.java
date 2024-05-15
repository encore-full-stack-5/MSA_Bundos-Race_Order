package com.bundosRace.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_ORDER")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @Column(name = "TOTAL_PRICE", nullable = false)
    @Setter
    private int totalPrice;

    @Column(name = "ORDERS_AMOUNT", nullable = false)
    private int orderAmount;

    @Column(name = "DELIVERY_MEMO")
    @Setter
    private String deliveryMemo;

    @Column(name = "REVIEW_CHECK")
    @ColumnDefault("false")
    @Setter
    private Boolean reviewCheck;

    @Column(name = "CREATE_AT", nullable = false)
    private LocalDate createAt;

    @Column(name = "USER_ID")
    private UUID userId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;
}
