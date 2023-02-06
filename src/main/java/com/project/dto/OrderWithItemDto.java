package com.project.dto;

import com.project.domain.Order;
import com.project.domain.constant.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderWithItemDto(
        Long orderId,
        String userName,
        List<OrderItemDto> items,
        OrderStatus orderStatus,
        LocalDateTime orderDate
        )
{
    public static OrderWithItemDto of(Long orderId, String userName, List<OrderItemDto> items, OrderStatus orderStatus, LocalDateTime orderDate) {
        return new OrderWithItemDto(orderId, userName, items, orderStatus, orderDate);
    }

    public static OrderWithItemDto from(Order entity) {
        return new OrderWithItemDto(
                entity.getId(),
                entity.getUserAccount().getUserId(),
                entity.getOrderItems().stream()
                        .map(OrderItemDto::from)
                        .toList(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}
