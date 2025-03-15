package com.mobile.service.impl;

import com.mobile.dto.category.CategoryDto;
import com.mobile.enumeration.ErrorCode;
import com.mobile.exception.AppException;
import com.mobile.form.category.CreateCategoryForm;
import com.mobile.form.category.UpdateCategoryForm;
import com.mobile.mapper.CategoryMapper;
import com.mobile.model.Category;
import com.mobile.repository.CategoryRepository;
import com.mobile.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto createCategory(CreateCategoryForm form) {
        if (categoryRepository.existsByName(form.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXITED);
        }
        Category category = categoryMapper.toEntity(form);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(UpdateCategoryForm form) {
        if (categoryRepository.existsByName(form.getName())) {
            throw new AppException(ErrorCode.CATEGORY_EXITED);
        }
        Category category = categoryRepository.findById(form.getId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        category.setName(form.getName());
        category.setIcon(form.getIcon());
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        categoryRepository.delete(category);
    }
}
