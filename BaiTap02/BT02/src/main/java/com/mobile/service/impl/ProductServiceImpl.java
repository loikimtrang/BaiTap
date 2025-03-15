package com.mobile.service.impl;

import com.mobile.dto.product.ProductDto;
import com.mobile.enumeration.ErrorCode;
import com.mobile.exception.AppException;
import com.mobile.form.product.CreateProductForm;
import com.mobile.mapper.ProductMapper;
import com.mobile.model.Category;
import com.mobile.model.Product;
import com.mobile.repository.CategoryRepository;
import com.mobile.repository.ProductRepository;
import com.mobile.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    ProductMapper productMapper;

    @Override
    public ProductDto createProduct(CreateProductForm form) {
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Product product = productMapper.toEntity(form);
        product.setCategory(category);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_FOUND);
        }
        return productRepository.findAllByCategoryId(categoryId).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getTopSellingProducts(int limit) {
        return productRepository.findTopSellingProducts(limit).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getRecentProducts(int dayLimit) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -dayLimit);
        Date startDate = calendar.getTime();
        return productRepository.findRecentProducts(startDate).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
