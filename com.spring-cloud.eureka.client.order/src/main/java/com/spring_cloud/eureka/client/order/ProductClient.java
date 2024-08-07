package com.spring_cloud.eureka.client.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 유레카에서 product-service 를 찾아서 요청들어갈 것
@FeignClient(name = "product-service")
public interface ProductClient {
    @GetMapping("/product/{id}")
    String getProduct(@PathVariable("id") String id);
}