package com.project.dto.request;

import com.project.domain.constant.OrderStatus;
import lombok.Data;

@Data
public class OrderRequest {
    private String userId;
    private OrderStatus orderStatus;
}
