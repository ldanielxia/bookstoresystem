package com.team15.bookstoreapp.exception;

public class BaseException extends  Exception {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BaseException(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    private  int code;
    private  String msg;

}
