package com.qinyue.monitor.my;

import java.io.Serializable;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/3
 * 描述:
 **/
public class YyDetailsBean implements Serializable {

    /**
     * gender : 男
     * nation : 汉族
     * isaudit : null
     * source : 小程序
     * type : 视频
     * content : 觉得就觉得
     * caseType : 控告类
     * lastUpdated : 2020-04-02T10:14:18Z
     * dateCreated : 2020-04-02T10:14:18Z
     * orgunit : 男的女的呢
     * baseUser : {"id":"402881f07119b17501711a9e413c0007","class":"com.bjrxkj.core.BaseUser"}
     * id : 23
     * reply : null
     * class : com.bjrxkj.petition.Reservation
     * email : a15598241114@gmail.com
     * replyDate : null
     * cardCode : 152634199512032412
     * cardType : 居民身份证
     * home : 姐姐家
     * receiveTime : 2020-04-01T16:00:00Z
     * nationality : 中国
     * phone : 15598241114
     * organization : 北京市院
     * name : 孟嘉
     * status : 待办理
     */

    private String gender;
    private String nation;
    private boolean isaudit;
    private String source;
    private String type;
    private String content;
    private String caseType;
    private String lastUpdated;
    private String dateCreated;
    private String orgunit;
    private BaseUserBean baseUser;
    private int id;
    private String reply;
    private String email;
    private String replyDate;
    private String cardCode;
    private String cardType;
    private String home;
    private String receiveTime;
    private String nationality;
    private String phone;
    private String organization;
    private String name;
    private String status;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public boolean getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(boolean isaudit) {
        this.isaudit = isaudit;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getOrgunit() {
        return orgunit;
    }

    public void setOrgunit(String orgunit) {
        this.orgunit = orgunit;
    }

    public BaseUserBean getBaseUser() {
        return baseUser;
    }

    public void setBaseUser(BaseUserBean baseUser) {
        this.baseUser = baseUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class BaseUserBean {
    }
}
