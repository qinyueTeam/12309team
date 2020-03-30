package com.qinyue.monitor.first;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class ZyajBean implements Serializable {
    //点击量
    private int hitCount;
    private String id;
    //案件所属机构
    private String orgName;
    private String pubDate;
    private String pubStatus;
    //发布者
    private String pubUserName;
    private String title;
    private ZyajMsgBean manuscript;

    public ZyajMsgBean getZyajMsgBean() {
        return manuscript;
    }

    public void setZyajMsgBean(ZyajMsgBean zyajMsgBean) {
        this.manuscript = zyajMsgBean;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubStatus() {
        return pubStatus;
    }

    public void setPubStatus(String pubStatus) {
        this.pubStatus = pubStatus;
    }

    public String getPubUserName() {
        return pubUserName;
    }

    public void setPubUserName(String pubUserName) {
        this.pubUserName = pubUserName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
