package com.project.service;

import com.project.domain.Item;
import com.project.domain.UserAccount;
import com.project.dto.ItemDto;
import com.project.dto.UserAccountDto;
import com.project.dto.request.ItemRequest;
import com.project.dto.request.ItemSearch;
import com.project.dto.security.ShopPrincipal;
import com.project.repository.ItemRepository;
import com.project.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserAccountRepository userAccountRepository;


    public void saveItem(ItemDto dto) {
        UserAccount user = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        itemRepository.save(dto.toEntity(user));
    }

    public void updateItem(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 글입니다."));
        item.edit(itemDto);
    }
    public void deleteItem(Long id) {
        Item item = itemRepository.getReferenceById(id);
        itemRepository.delete(item);
    }

    @Transactional(readOnly = true)
    public Page<ItemDto> searchItems(ItemSearch itemSearch, Pageable pageable) {

        return itemRepository.searchItems(itemSearch, pageable)
                .map(ItemDto::from);
    }

    @Transactional(readOnly = true)
    public ItemDto findOne(Long id) {
        return ItemDto.from(itemRepository.getReferenceById(id));
    }

    @Transactional(readOnly = true)
    public Page<ItemDto> findById(UserAccountDto userAccountDto, Pageable pageable) {
        return itemRepository.findByUserAccount_UserId(userAccountDto.toEntity().getUserId(), pageable)
                .map(ItemDto::from);
    }
    @Transactional(readOnly = true)
    public List<ItemDto> findItems() {
        return itemRepository.findAll().stream()
                .map(ItemDto::from).toList();
    }
}

