package com.team15.bookstoreapp.model;

public enum CodeEntity implements ICodeEnum {
    SUCCESS_EXCEPTION(1, "get data success"),
    INSERT_EXCEPTION(100, "insert data success"),
    UPDATE_EXCEPTION(101, "update data success"),
    DELETE_EXCEPTION(102, "update data success"),
    VERIFY_EXCEPTION(5001, "valid false"),
    COMMON_EXCEPTION(2001, "system eroor"),
    FALSE_EXCEPTION(1001, "get data false");

    //error code
    public Integer code;
    //tips
    public String message;

    CodeEntity(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
