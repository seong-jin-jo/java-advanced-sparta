package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.entity.OrderProduct;
import com.sparta.msa_exam.order.repository.OrderProductRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import com.sparta.msa_exam.order.service.ProductClient;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    public boolean updateOrder(Long orderId, Long productId){
        // 상품 존재 여부 확인
        System.out.println(String.valueOf(productId));
        String product = productClient.getProduct(String.valueOf(productId));

        // 상품이 존재하면 주문에 추가 (로직은 실제로 구현되어야 함)
        if (product != null && !product.isEmpty()) {
            Order order = orderRepository.findByOrderId(orderId);
            if (order != null) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct_id(productId);
                orderProduct.setOrder(order);

                orderProductRepository.save(orderProduct);
                return true;  // 상품 추가 성공
            }
            return true;  // 상품 추가 성공
        }

        // 상품이 존재하지 않으면 추가하지 않음
        return false;
    }

    public String getProductInfo(String productId) {
        return productClient.getProduct(productId);
    }

    public Order saveOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setName(orderRequestDto.getName());

        // order 테이블 orderId 먼저 저장
        Order savedOrder = orderRepository.save(order);

        List<OrderProduct> orderProducts = orderRequestDto.getProduct_ids().stream()
                .map(productId -> {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProduct_id(productId);
                    orderProduct.setOrder(savedOrder);
                    return orderProduct;
                })
                .collect(Collectors.toList());
        order.setProduct_ids(orderProducts);

        // orderProduct 테이블에 orderId-productId 매핑관계 저장
        orderProductRepository.saveAll(orderProducts);
        return savedOrder;
    }

    /**
     * 주문 단건 조회 API
     * @param orderId
     * @return String order_id, List<OrderProduct> product_ids
     */
    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrder_id(order.getOrder_id());

        // OrderProduct 리스트에서 product_id 추출
        List<Long> productIds = order.getProduct_ids().stream()
                .map(OrderProduct::getProduct_id)
                .collect(Collectors.toList());

        orderResponseDto.setProduct_ids(productIds);

        return orderResponseDto;
    }
}