package com.mobile.enumeration;

public enum ErrorCode {
    INVALID_FORM("ERROR", "Invalid form"),
    UNAUTHENTICATED("ERROR", "Unauthenticated"),
    CATEGORY_EXITED("ERROR", "Category name already exists"),
    CATEGORY_NOT_FOUND("NOT_FOUND", "Category not found"),
    ;

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
