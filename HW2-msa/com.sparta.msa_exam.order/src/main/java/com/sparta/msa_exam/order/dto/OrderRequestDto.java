package com.sparta.msa_exam.order.dto;

import com.sparta.msa_exam.order.entity.OrderProduct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderRequestDto {
    private String name;
    private List<Long> product_ids;

}

