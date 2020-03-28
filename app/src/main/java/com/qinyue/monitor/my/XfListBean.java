package com.qinyue.monitor.my;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class XfListBean implements Serializable {
    private int id;
    private String caseType;
    private String organization;
    private String status;
    private String score;
    private String content;//普通信访内容
    private String petitionType;//普通信访类型,未成年
    private String dateCreated;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(String petitionType) {
        this.petitionType = petitionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
