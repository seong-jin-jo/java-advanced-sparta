package com.sparta.msa_exam.order.repository;

import com.sparta.msa_exam.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.order_id = :order_id")
    Order findByOrderId(Long order_id);
}
