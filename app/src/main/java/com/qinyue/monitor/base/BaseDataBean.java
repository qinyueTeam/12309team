package com.qinyue.monitor.base;

import java.io.Serializable;

/**
 * 基本数据格式化base类
 * @param <T>
 */
public class BaseDataBean<T> implements Serializable {
    private boolean result;
    private String message = "";
    private T data = null;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        if ("接口调用成功".equals(message)){
            return "操作成功";
        }
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
