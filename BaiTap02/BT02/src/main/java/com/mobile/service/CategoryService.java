package com.mobile.service;

import com.mobile.dto.category.CategoryDto;
import com.mobile.form.category.CreateCategoryForm;
import com.mobile.form.category.UpdateCategoryForm;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CreateCategoryForm form);

    List<CategoryDto> getAllCategories();

    CategoryDto updateCategory(UpdateCategoryForm form);

    void deleteCategory(Long id);
}
