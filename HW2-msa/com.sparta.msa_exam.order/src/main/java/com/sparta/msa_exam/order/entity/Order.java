package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    // product 의 id 들이 저장됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 테이블이름이 아니라 entity OrderProduct 클래스의 필드 이름
    private List<OrderProduct> product_ids;

}