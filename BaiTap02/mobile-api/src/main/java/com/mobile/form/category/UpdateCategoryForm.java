package com.mobile.form.category;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class UpdateCategoryForm {

    @NotNull(message = "Category id can not be null or empty")
    Long id;

    @NotEmpty(message = "Category name can not be null or empty")
    String name;

    @NotEmpty(message = "Category icon can not be null or empty")
    String icon;

}
