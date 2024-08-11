package com.sparta.msa_exam.order.controller;


import com.sparta.msa_exam.order.dto.OrderProductRequestDto;
import com.sparta.msa_exam.order.dto.OrderRequestDto;
import com.sparta.msa_exam.order.dto.OrderResponseDto;
import com.sparta.msa_exam.order.entity.Order;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * 주문 추가 API
     * @param orderRequestDto
     * @return Order
     */
    @PostMapping("/order")
    public Order saveOrder(@RequestBody OrderRequestDto orderRequestDto){
        System.out.println(orderRequestDto.toString());
        return orderService.saveOrder(orderRequestDto);
    }

    /**
     * 주문에 상품을 추가하는 API
     * @param orderId
     * @param orderProductRequestDto
     * @return
     */
    @PutMapping("/order/{orderId}")
    public String updateOrder(@PathVariable Long orderId, @RequestBody OrderProductRequestDto orderProductRequestDto) {
        Long productId = orderProductRequestDto.getProduct_id();

        boolean isProductAdded = orderService.updateOrder(orderId, productId);
        if (isProductAdded) {
            return "Product added to order successfully.";
        } else {
            return "Product does not exist. Order was not updated.";
        }
    }

    /**
     * 주문 단건 조회 API
     *
     * @param orderId
     * @return
     */
    @GetMapping("/order/{orderId}")
    public OrderResponseDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

}
