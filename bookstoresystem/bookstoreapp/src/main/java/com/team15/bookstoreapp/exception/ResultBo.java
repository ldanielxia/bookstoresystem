package com.team15.bookstoreapp.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Unified business logic object returned by back-end interface
 */
public class ResultBo<T> {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private int code;

    private String msg;

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getPageindex() {
        return _pageindex;
    }

    public void setPageindex(int index) {
        this._pageindex = index;
    }

    public int getPagesize() {
        return _pagesize;
    }

    public void setPagesize(int size) {
        this._pagesize = size;
    }

    private   int _pageindex;
    private   int _pagesize;

    private int recordsTotal;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    private int draw;

    private T data;


    public ResultBo() {
        this.code = 0;
        this.msg = "success action";
    }


    public ResultBo(T data) {
        this();
        this.data = data;
    }
    public ResultBo(T data, int recordsTotal, int draw ) {
        this();
        this.data = data;
        this.recordsTotal=recordsTotal;
        this.draw=draw;
    }

    public ResultBo(Exception ex) {
        List<Map<String, Object>> list=new ArrayList<>(); ;
        if (ex instanceof BaseException) {
            this.code = ((BaseException) ex).getCode();
            this.msg = ex.getMessage();

        } else {
            this.code = 99999;// other exception
            this.msg = ex.getMessage();

        }
    }

    public ResultBo(Exception ex, T Data) {

        if (ex instanceof BaseException) {
            this.code = ((BaseException) ex).getCode();
            this.msg = ((BaseException) ex).getMsg();
            this.data=Data;
        } else {
            this.code = 99999;// other exception
            this.msg = ex.getMessage();
            this.data=Data;
        }
    }

    public ResultBo(Exception ex, T Data, int recordsTotal ) {

        if (ex instanceof BaseException) {
            this.code = ((BaseException) ex).getCode();
            this.msg = ((BaseException) ex).getMsg();
            this.data=Data;
            this.recordsTotal=recordsTotal;
        } else {
            this.code = 99999;// other exception
            this.msg = ex.getMessage();
            this.data=Data;
        }
    }
}
