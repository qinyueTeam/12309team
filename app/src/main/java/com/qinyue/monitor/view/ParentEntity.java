package com.qinyue.monitor.view;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class ParentEntity implements Serializable {
    public static final int PARENT_ITEM = 0;//父布局
    public static final int CHILD_ITEM = 1;//子布局

    private int type;//显示类型
    private boolean isExpand;// 是否展开
    private String code;
    private String name;
    private String firstCode;
    private String secCode;
    private String thCode;
    private boolean hasChild;
    private int lv;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstCode() {
        return firstCode;
    }

    public void setFirstCode(String firstCode) {
        this.firstCode = firstCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getThCode() {
        return thCode;
    }

    public void setThCode(String thCode) {
        this.thCode = thCode;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
