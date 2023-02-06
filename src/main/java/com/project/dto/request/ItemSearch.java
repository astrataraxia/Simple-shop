package com.project.dto.request;

import com.project.domain.constant.SearchType;
import lombok.Data;

@Data
public class ItemSearch {

    private SearchType type;
    private String keyword;
}
