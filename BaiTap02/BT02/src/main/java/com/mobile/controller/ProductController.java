package com.mobile.controller;

import com.mobile.dto.ApiMessageDto;
import com.mobile.dto.product.ProductDto;
import com.mobile.form.product.CreateProductForm;
import com.mobile.service.ProductService;
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
@RequestMapping(path = "/api/product")
public class ProductController {

    ProductService productService;

    @PostMapping(path = "/create")
    public ResponseEntity<ApiMessageDto<ProductDto>> create(@RequestBody @Valid CreateProductForm form) {
        ApiMessageDto<ProductDto> response = ApiMessageDto.<ProductDto>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Create product successfully")
                .data(productService.createProduct(form))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity<ApiMessageDto<List<ProductDto>>> getAll() {
        ApiMessageDto<List<ProductDto>> response = ApiMessageDto.<List<ProductDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message("Get all products successfully")
                .data(productService.getAllProducts())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-by-category/{categoryId}")
    public ResponseEntity<ApiMessageDto<List<ProductDto>>> getAllByCategory(@PathVariable Long categoryId) {
        ApiMessageDto<List<ProductDto>> response = ApiMessageDto.<List<ProductDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message(String.format("Get all products for category ID %d successfully", categoryId))
                .data(productService.getProductsByCategory(categoryId))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-top-selling")
    public ResponseEntity<ApiMessageDto<List<ProductDto>>> getTopSelling() {
        int limit = 10;
        ApiMessageDto<List<ProductDto>> response = ApiMessageDto.<List<ProductDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message(String.format("Get top %d selling products successfully", limit))
                .data(productService.getTopSellingProducts(limit))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/get-recent-product")
    public ResponseEntity<ApiMessageDto<List<ProductDto>>> getRecentProduct() {
        int dayLimit = 7;
        ApiMessageDto<List<ProductDto>> response = ApiMessageDto.<List<ProductDto>>builder()
                .result(true)
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .message(String.format("Products created within the last %d successfully", dayLimit))
                .data(productService.getRecentProducts(dayLimit))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
