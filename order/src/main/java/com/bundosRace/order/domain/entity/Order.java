package com.bundosRace.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT_ORDER")
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOTAL_PRICE", nullable = false)
    @Setter
    private Long totalPrice;

    @Column(name = "ORDERS_AMOUNT", nullable = false)
    @Setter
    private int orderAmount;

    @Column(name = "DELIVERY_MEMO")
    @Setter
    private String deliveryMemo;

    @Column(name = "CREATE_AT", nullable = false)
    private LocalDate createAt;

    @Column(name = "UPDATE_AT")
    @Setter
    private LocalDate updateAt;

    @Column(name = "ORDER_EXIST")
    private int orderExist;
}
