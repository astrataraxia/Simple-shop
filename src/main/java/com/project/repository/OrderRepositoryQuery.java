package com.project.repository;

import com.project.domain.Order;
import com.project.domain.constant.OrderStatus;
import com.project.dto.request.OrderRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.project.domain.QOrder.order;
import static com.project.domain.QUserAccount.userAccount;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryQuery {

    private final JPAQueryFactory queryFactory;


    public List<Order> findOrders(OrderRequest orderRequest) {
        return queryFactory
                .selectFrom(order)
                .join(order.userAccount, userAccount)
                .where(statusEq(orderRequest.getOrderStatus()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression statusEq(OrderStatus statusCond) {
        if (statusCond == null) {
            return null;
        }
        return order.status.eq(statusCond);
    }
}
