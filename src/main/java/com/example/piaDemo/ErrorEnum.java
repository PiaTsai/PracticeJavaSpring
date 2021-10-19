package com.example.piaDemo;

public enum ErrorEnum {
    NO_ERROR(0, ""),
    DUPLICATE_MEMBER(1, "This account already exists."),
    MISSING_PARAMS(2, "Missing parameters."),
    PHONE_ERROR(3, "Phone number error."),
    PASSWORD_ERROR(4, "Account or password error."),
    DB_ERROR(5, "Database Error."),
    NO_MEMBER(6, "Account is not exist.");


    private final int code;
    private final String description;

    private ErrorEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;

    }
}