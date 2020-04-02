package com.qinyue.monitor.login;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/1
 * 描述:
 **/
public class PhoneBean implements Serializable {
    private String phone;
    private String username;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
