package com.project.dto;

import com.project.domain.OrderItem;

public record OrderItemDto(
         String itemName,
         int orderPrice,
         int count
) {

    public static OrderItemDto of(String itemName, int OrderPrice, int count) {
        return new OrderItemDto(itemName, OrderPrice, count);
    }

    public static OrderItemDto from(OrderItem entity) {
        return new OrderItemDto(
                entity.getItem().getName(),
                entity.getOrderPrice(),
                entity.getCount()
        );
    }
}
