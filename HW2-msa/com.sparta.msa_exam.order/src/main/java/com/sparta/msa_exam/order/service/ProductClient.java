package com.sparta.msa_exam.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

// 유레카에서 product-service 를 찾아서 요청들어갈 것
@FeignClient(name = "product-service-hw")
public interface ProductClient {

    @GetMapping("/products/{id}")
    String getProduct(@PathVariable("id") String id);

    @PostMapping("/products")
    String saveProduct(@PathVariable("id") String id);
}