package com.project.repository;

import com.project.domain.Order;
import com.project.dto.OrderWithItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserAccount_userId(String userId);
}

