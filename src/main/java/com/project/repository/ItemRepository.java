package com.project.repository;

import com.project.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>,ItemRepositoryCustom {

    Page<Item> findByUserAccount_UserId(String userId, Pageable pageable);
}
