package com.sparta.msa_exam.order.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderProductRequestDto {
    private Long product_id; // OrderProduct 엔티티의 product_id만 포함
}