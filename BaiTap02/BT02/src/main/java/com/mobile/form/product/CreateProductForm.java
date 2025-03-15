package com.mobile.form.product;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateProductForm {
    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 500, message = "Product name must not exceed 500 characters")
    String productName;

    @NotNull(message = "Quantity cannot be null")
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    Long quantity;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    Double unitPrice;

    @Size(max = 200, message = "Image URL must not exceed 200 characters")
    String image;

    @NotBlank(message = "Description cannot be empty")
    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description;

    @NotNull(message = "Discount cannot be null")
    @DecimalMin(value = "0.0", message = "Discount must be greater than or equal to 0")
    Double discount;

    @NotNull(message = "Creation date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createDate;

    @NotNull(message = "Status cannot be null")
    Short status;

    @NotNull(message = "Category cannot be null")
    Long categoryId;

}
