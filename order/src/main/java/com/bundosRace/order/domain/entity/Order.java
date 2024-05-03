package com.bundosRace.order.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String address;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Long totalPrice;

    @Column(name = "ORDER_AMOUNT", nullable = false)
    private int orderAmount;

    @Column(name = "CREATE_AT")
    private LocalDate createAt;

    @Column(name = "REQUEST_LIST")
    @CreationTimestamp
    private String requestList;

    @Column(name = "order_exist")
    @ColumnDefault("false")
    private boolean orderExist;
}
