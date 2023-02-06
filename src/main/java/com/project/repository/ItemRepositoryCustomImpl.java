package com.project.repository;

import com.project.domain.Item;
import com.project.domain.QUserAccount;
import com.project.domain.UserAccount;
import com.project.domain.constant.SearchType;
import com.project.dto.request.ItemSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.project.domain.QItem.*;
import static com.project.domain.QUserAccount.*;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> searchItems(ItemSearch itemSearch, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(item)
                .join(item.userAccount, userAccount)
                .where(
                        userIdEq(itemSearch.getType(),itemSearch.getKeyword()),
                        itemNameEq(itemSearch.getType(),itemSearch.getKeyword())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.createdAt.desc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.id.count())
                .from(item)
                .where(
                        userIdEq(itemSearch.getType(),itemSearch.getKeyword()),
                        itemNameEq(itemSearch.getType(),itemSearch.getKeyword())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression itemNameEq(SearchType type, String keyWord) {

        return type == SearchType.ITEM && hasText(keyWord)
                ? item.name.like("%"+keyWord+"%") : null;
    }


    private BooleanExpression userIdEq(SearchType type, String keyWord) {

        return type == SearchType.ID && hasText(keyWord)
                ? item.userAccount.nickname.like(keyWord) : null;
    }
}
