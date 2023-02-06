package com.project.domain;

import com.project.domain.constant.DeliveryStatus;
import com.project.domain.constant.OrderStatus;
import com.project.exception.CanNotCancelException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AuditingFields{
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @Setter
    @OneToOne(cascade = ALL, fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order",cascade = ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Setter
    @Enumerated(STRING)
    private OrderStatus status;  //ORDER, CANCEL


    public void setUserAccount (UserAccount userAccount) {
        this.userAccount = userAccount;
        userAccount.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(UserAccount user, Delivery delivery, OrderItem orderItem) {
        Order order = new Order();
        order.setUserAccount(user);
        order.setDelivery(delivery);
        order.addOrderItem(orderItem);
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMPLETE) {
            throw new CanNotCancelException();
        }

        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }

    public int getTotalPrice() {
        return orderItems.stream().
                mapToInt(OrderItem::getTotalPrice)
                .sum();
    }
}
