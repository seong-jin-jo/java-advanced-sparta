package com.sparta.msa_exam.order.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orderproduct")
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    // ManyToOne 에 의해 실제 Order 객체를 참조중
    // DB와 CRUD 시에는 Order 객체의 상태를 기반으로 order_id 값을 정하고 DB의 order_id 칼럼에 저장
    private Order order;

    @Column(name = "product_id", nullable = false)
    // MSA 아키텍쳐이므로 Product 는 id만 저장한듯
    private Long product_id;
}
