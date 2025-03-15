package com.mobile.dto.category;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class CategoryDto {
    Long categoryId;
    String categoryName;
    String categoryIcon;
}
