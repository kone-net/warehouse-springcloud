package com.kone.utils.msg;

import com.kone.utils.pager.Pager;

public class ResponseMsg<T> {

    private Integer code = 200;

    private String msg = "success";

    private T data;

    private Pager pager;

    private Float sum;

    public ResponseMsg() {
    }

    public ResponseMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseMsg(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
