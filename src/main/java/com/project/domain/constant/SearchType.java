package com.project.domain.constant;

import lombok.Getter;

public enum SearchType {
    ID("판매자"),
    ITEM("상품명");


    @Getter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
