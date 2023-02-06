package com.project.controller;

import com.project.dto.ItemDto;
import com.project.dto.OrderWithItemDto;
import com.project.dto.request.OrderRequest;
import com.project.dto.UserAccountDto;
import com.project.dto.security.ShopPrincipal;
import com.project.service.ItemService;
import com.project.service.OrderService;
import com.project.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final OrderService orderService;
    private final UserAccountService userAccountService;

    @GetMapping("/order")
    public String createOrder(@AuthenticationPrincipal ShopPrincipal shopPrincipal, Model model) {
        List<ItemDto> items = itemService.findItems();
        UserAccountDto user = userAccountService.findUsers(shopPrincipal.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("items", items);

        return "order/order";
    }

    @PostMapping("/order")
    public String order(@RequestParam String userId,
                        @RequestParam Long itemId,
                        @RequestParam int count) {
        orderService.order(userId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderRequest orderRequest,
                            @AuthenticationPrincipal ShopPrincipal shopPrincipal,
                            Model model) {
        orderRequest.setUserId(shopPrincipal.getUsername());
        List<OrderWithItemDto> orders = orderService.findOrders(orderRequest);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
