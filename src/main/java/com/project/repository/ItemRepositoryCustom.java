package com.project.repository;

import com.project.domain.Item;
import com.project.domain.UserAccount;
import com.project.dto.request.ItemSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> searchItems(ItemSearch itemSearch, Pageable pageable);
}
