package com.sparta.msa_exam.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor // 기본생성자 만들어줌
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;

    @Column(name = "supply_price", nullable = false)
    private Integer supply_price;
}