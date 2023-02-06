package com.project.dto.response;

import com.project.dto.ItemDto;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


public record ItemResponse(
        Long id,
        String name,
        int price,
        int quantity,
        String direction,
        String userId,
        String nickname,
        LocalDateTime createdAt
) {


    public static ItemResponse of(Long id, String name, int price, int quantity, String direction, String userId, String nickname, LocalDateTime createdAt) {
        return new ItemResponse(id, name, price, quantity, direction, userId,nickname, createdAt);
    }

    public static ItemResponse from(ItemDto dto) {
        String nickname = dto.userAccountDto().nickname();
        if (nickname == null || nickname.isBlank()) {
            nickname = dto.userAccountDto().userId();
        }

        return new ItemResponse(
                dto.id(),
                dto.name(),
                dto.price(),
                dto.quantity(),
                dto.direction(),
                dto.userAccountDto().userId(),
                nickname,
                dto.createdAt()
        );
    }
}
