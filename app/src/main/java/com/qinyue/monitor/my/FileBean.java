package com.qinyue.monitor.my;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class FileBean implements Serializable {
    private String fieldNames;
    private String fieldUrls;

    public String getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(String fieldNames) {
        this.fieldNames = fieldNames;
    }

    public String getFieldUrls() {
        return fieldUrls;
    }

    public void setFieldUrls(String fieldUrls) {
        this.fieldUrls = fieldUrls;
    }
}
