package com.project.controller;

import com.project.dto.ItemDto;
import com.project.dto.UserAccountDto;
import com.project.dto.request.ItemRequest;
import com.project.dto.request.ItemSearch;
import com.project.dto.response.ItemResponse;
import com.project.dto.security.ShopPrincipal;
import com.project.service.ItemService;
import com.project.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final PaginationService paginationService;

    @GetMapping
    public String items( //아이템 리스트
            @ModelAttribute("search") ItemSearch itemSearch,
            @PageableDefault(size=10) Pageable pageable,
            Model model) {
        Page<ItemResponse> items = itemService.searchItems(itemSearch,pageable).map(ItemResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), items.getTotalPages());

        model.addAttribute("items", items);
        model.addAttribute("pagination", barNumbers);

        return "items/itemList";
    }

    @GetMapping("/list")
    public String item( @AuthenticationPrincipal ShopPrincipal shopPrincipal,
                       @PageableDefault(size=10) Pageable pageable,
                       Model model) {

        Page<ItemResponse> items = itemService.findById(shopPrincipal.toDto(), pageable)
                .map(ItemResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), items.getTotalPages());

        model.addAttribute("item", items);
        model.addAttribute("pagination", barNumbers);
        return "items/item";
    }

    @PostMapping("/list")
    public String deleteItem(Long id) {
        itemService.deleteItem(id);
        return "redirect:/items/list";
    }

    @GetMapping("/new")
    public String itemForm(Model model) {
        model.addAttribute("item", new ItemRequest());
        return "items/createItemForm";
    }

    @PostMapping("/new")
    public String postItemForm(
            @AuthenticationPrincipal ShopPrincipal shopPrincipal,
            @ModelAttribute("item") ItemRequest itemRequest) {
        itemService.saveItem(itemRequest.toDto(shopPrincipal.toDto()));
        return "redirect:/items";
    }

    @GetMapping("/{id}/edit")
    public String editItem(@PathVariable Long id, Model model) {
        ItemDto item = itemService.findOne(id);
        ItemRequest form = ItemRequest.of(item.id(),item.name(), item.price(), item.quantity(), item.direction(), item.userAccountDto());
        model.addAttribute("item", form);
        return "items/edit-item";
    }

    @PostMapping("/{id}/edit")
    public String editItem(@PathVariable Long id,
                           @ModelAttribute("item") ItemRequest itemRequest,
                           @AuthenticationPrincipal ShopPrincipal shopPrincipal) {
        itemService.updateItem(id,itemRequest.toDto(shopPrincipal.toDto()));

        return "redirect:/items/list";
    }
}
