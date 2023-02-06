package com.project.domain;

import com.project.dto.ItemDto;
import com.project.exception.NotEnoughStockException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends AuditingFields {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;
    private int price;
    private int quantity;

    private String direction;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    private Item(String name, int price, int quantity, String direction, UserAccount userAccount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.direction = direction;
        this.userAccount = userAccount;
    }

    public static Item of(String name, int price, int quantity, String direction, UserAccount userAccount) {
        return new Item(name, price, quantity, direction, userAccount);
    }

    public void edit(ItemDto itemDto) {
        name = itemDto.name();
        price = itemDto.price();
        quantity = itemDto.quantity();
        direction = itemDto.direction();
        userAccount = itemDto.userAccountDto().toEntity();
    }

    public void addStock(int count) {
        this.quantity += count;
    }

    public void removeStock(int count) {
        int restStock = this.quantity - count;
        if (restStock < 0) {
            throw new NotEnoughStockException();
        }
        this.quantity = restStock;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return id != null && id.equals(item.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}