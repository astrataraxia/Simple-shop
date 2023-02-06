package com.project.service;

import com.project.domain.*;
import com.project.dto.OrderWithItemDto;
import com.project.dto.request.OrderRequest;
import com.project.repository.ItemRepository;
import com.project.repository.OrderRepository;
import com.project.repository.OrderRepositoryQuery;
import com.project.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositoryQuery orderRepositoryQuery;
    private final UserAccountRepository userAccountRepository;
    private final ItemRepository itemRepository;

    public Long order(String userId, Long itemId, int count) {
        UserAccount user = userAccountRepository.getReferenceById(userId);
        Item item = itemRepository.getReferenceById(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(user.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(user, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional(readOnly = true)
    public List<OrderWithItemDto> findOrders(OrderRequest orderRequest) {
        return orderRepositoryQuery.findOrders(orderRequest)
                .stream().map(OrderWithItemDto::from).toList();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancel();
    }
}
