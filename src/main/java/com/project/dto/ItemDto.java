package com.project.dto;

import com.project.domain.Item;
import com.project.domain.UserAccount;

import java.time.LocalDateTime;

public record ItemDto(
        Long id,
        String name,
        int price,
        int quantity,
        String direction,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ItemDto of(String name, int price, int quantity, String direction, UserAccountDto userAccountDto) {
        return new ItemDto(null, name, price, quantity, direction, userAccountDto, null, null, null, null);
    }


    public static ItemDto of(Long id, String name, int price, int quantity, String direction, UserAccountDto userAccountDto,
                      LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ItemDto(id, name, price, quantity, direction, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ItemDto from(Item entity) {
        return new ItemDto(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getQuantity(),
                entity.getDirection(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Item toEntity(UserAccount userAccount) {
        return Item.of(
                name,
                price,
                quantity,
                direction,
                userAccount
        );
    }
}
