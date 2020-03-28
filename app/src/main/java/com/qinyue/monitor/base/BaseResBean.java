package com.qinyue.monitor.base;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class BaseResBean implements Serializable {
    private int result;
    private String message;
    private String data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
