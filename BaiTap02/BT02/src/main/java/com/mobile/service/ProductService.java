package com.mobile.service;

import com.mobile.dto.product.ProductDto;
import com.mobile.form.product.CreateProductForm;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(CreateProductForm form);

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getTopSellingProducts(int limit);

    List<ProductDto> getRecentProducts(int dayLimit);

}
