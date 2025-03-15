package com.mobile.form.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class CreateCategoryForm {

    @NotBlank(message = "Category name can not be null or empty")
    @Size(max = 500, message = "Category name must not exceed 500 characters")
    String name;

    @NotBlank(message = "Category icon can not be null or empty")
    @Size(max = 200, message = "Image URL must not exceed 200 characters")
    String icon;
}
