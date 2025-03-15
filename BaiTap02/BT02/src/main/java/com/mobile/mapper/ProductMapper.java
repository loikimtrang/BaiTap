package com.mobile.mapper;

import com.mobile.dto.product.ProductDto;
import com.mobile.form.product.CreateProductForm;
import com.mobile.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "productName", target = "name")
    Product toEntity(CreateProductForm form);

    @Mapping(source = "name", target = "productName")
    ProductDto toDto(Product form);

}
