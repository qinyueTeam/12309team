package com.qinyue.monitor.my;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/29
 * 描述:
 **/
public class JczxxMsgBean implements Serializable {
    private String id;
    private String writer;
    private String sex;
    private String phone;
    private String content;
    private String organizationId;
    private String submitterId;
    private String dateCreated;
    private String lastUpdated;
    private String status;
    private String reply;
    private String replyPeople;
    private List<FileBean> filedList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(String submitterId) {
        this.submitterId = submitterId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyPeople() {
        return replyPeople;
    }

    public void setReplyPeople(String replyPeople) {
        this.replyPeople = replyPeople;
    }

    public List<FileBean> getFiledList() {
        return filedList;
    }

    public void setFiledList(List<FileBean> filedList) {
        this.filedList = filedList;
    }
}
