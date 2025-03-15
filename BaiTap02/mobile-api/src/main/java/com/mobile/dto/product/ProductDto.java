package com.mobile.dto.product;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
public class ProductDto {
    String productName;
    Long quantity;
    Double unitPrice;
    String image;
    String description;
    Double discount;
    Date createDate;
    Short status;
}
