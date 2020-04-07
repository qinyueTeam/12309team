package com.qinyue.monitor.base;

import java.io.Serializable;
import java.util.List;

/**
 * 基本数据格式化base类
 * @param <T>
 */
public class BaseArrayDataBean2<T> implements Serializable {
    private int result;
    private String msg = "";
    private List<T> data = null;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        if ("接口调用成功".equals(msg)){
            return "操作成功";
        }
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
