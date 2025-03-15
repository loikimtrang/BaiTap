package com.mobile.controller;

import com.mobile.dto.ApiMessageDto;
import com.mobile.dto.category.CategoryDto;
import com.mobile.form.category.CreateCategoryForm;
import com.mobile.form.category.UpdateCategoryForm;
import com.mobile.service.CategoryService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/category")
public class CategoryController {

    CategoryService categoryService;

    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<CategoryDto>> create(@RequestBody @Valid CreateCategoryForm form) {
        ApiMessageDto<CategoryDto> response = ApiMessageDto.<CategoryDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create category successfully")
                .data(categoryService.createCategory(form))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ApiMessageDto<List<CategoryDto>>> getAll() {
        ApiMessageDto<List<CategoryDto>> response = ApiMessageDto.<List<CategoryDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Get all categories successfully")
                .data(categoryService.getAllCategories())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ApiMessageDto<CategoryDto>> update(@RequestBody @Valid UpdateCategoryForm form) {
        ApiMessageDto<CategoryDto> response = ApiMessageDto.<CategoryDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Update category successfully")
                .data(categoryService.updateCategory(form))
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiMessageDto<Void>> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        ApiMessageDto<Void> response = ApiMessageDto.<Void>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.OK.value()))
                .message("Delete category successfully")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
