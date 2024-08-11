package com.sparta.msa_exam.product.controller;

import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다. (application.properties 에 server.port 로 지정한 포트 메타데이터)
    private String serverPort;

    /**
     * 상품 추가 API
     *
     * @param productRequestDto
     * @return
     */
    @PostMapping("/products")
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.saveProduct(productRequestDto);
    }

    /**
     * 상품 목록 조회 API (*********)
     *
     * @return
     */
    @GetMapping("/products/{id}")
    public List<Product> getProducts(@PathVariable String id) {
        logger.debug("dd"+id+"gg");
        logger.debug("getProducts 메서드 실행");
        return productService.getProduct(id);
    }
}
