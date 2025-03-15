package com.mobile.mapper;

import com.mobile.dto.category.CategoryDto;
import com.mobile.form.category.CreateCategoryForm;
import com.mobile.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CreateCategoryForm form);

    @Mapping(source = "id", target = "categoryId")
    @Mapping(source = "name", target = "categoryName")
    @Mapping(source = "icon", target = "categoryIcon")
    CategoryDto toDto(Category entity);

}
