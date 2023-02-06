package com.project.dto.request;

import com.project.dto.ItemDto;
import com.project.dto.UserAccountDto;
import lombok.Data;

@Data
public class ItemRequest {
    Long id;
    String name;
    int price;
    int quantity;
    String direction;
    UserAccountDto userAccountDto;

    public ItemRequest() {
    }

    private ItemRequest(Long id,String name, int price, int quantity, String direction, UserAccountDto userAccountDto) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.direction = direction;
        this.userAccountDto = userAccountDto;
    }

    public static ItemRequest of(Long id,String name, int price, int quantity, String direction, UserAccountDto userAccountDto) {
            return new ItemRequest(id,name, price, quantity, direction, userAccountDto);
        }

        public ItemDto toDto(UserAccountDto userAccountDto) {
            return ItemDto.of(
                    name,
                    price,
                    quantity,
                    direction,
                    userAccountDto
            );
    }

}
