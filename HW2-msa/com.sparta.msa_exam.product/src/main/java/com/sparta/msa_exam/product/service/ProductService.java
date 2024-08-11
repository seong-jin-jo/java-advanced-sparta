package com.sparta.msa_exam.product.service;

import com.sparta.msa_exam.product.ProductApplication;
import com.sparta.msa_exam.product.dto.ProductRequestDto;
import com.sparta.msa_exam.product.dto.ProductResponseDto;
import com.sparta.msa_exam.product.entity.Product;
import com.sparta.msa_exam.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor // 생성자 주입중
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * DB에서 상품조회 (**********)
     *
     * @return
     */
    public List<Product> getProduct(String id){
        return productRepository.findAllById(Collections.singleton(Integer.parseInt(id)));
    }

    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setSupply_price(productRequestDto.getSupply_price());

        Product productDB = productRepository.save(product);
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setName(productDB.getName());
        productResponseDto.setSupply_price(productDB.getSupply_price());

        return productResponseDto;
    }
}