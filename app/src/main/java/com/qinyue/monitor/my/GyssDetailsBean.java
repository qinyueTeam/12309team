package com.qinyue.monitor.my;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/3
 * 描述:
 **/
public class GyssDetailsBean implements Serializable {

    /**
     * caseTypeAudit : null
     * submitter : {"id":"402881f07119b17501711a9e413c0007","class":"com.bjrxkj.core.BaseUser"}
     * code : null
     * plaintiffCertificateNumber : null
     * breakLawOrg : null
     * source : 小程序
     * caseType : 2
     * feedback : null
     * plaintiffName : now
     * lastUpdated : 2020-04-03T07:23:17Z
     * score : null
     * dateCreated : 2020-04-03T07:23:17Z
     * plaintiffPhone : 18804916183
     * areaType : 1
     * defendantAddress : null
     * id : 36
     * isRepeat : null
     * picCreateTime : 2020-04-03T16:00:00Z
     * reply : null
     * class : com.bjrxkj.publicPetition.PublicPetition
     * isTrans : null
     * auditDate : null
     * breakLawOrgOther : null
     * unitName : null
     * replyDate : null
     * doOrg : null
     * defendantName : null
     * areaCity : 9913000110106
     * exportDate : null
     * organization : 北京市院
     * areaProv : 9913000110000
     * involveOrg : null
     * breakLawProperty : null
     * areaRemark : null
     * reflectRemark : 锦绣未央
     * harmRange : 3
     * plaintiffAddress : null
     * smscontent : null
     * status : 待办理
     * toOrg : {"id":"cbe523dfd3e24b83b95616492c56940d","class":"com.bjrxkj.dictionary.Organization"}
     * imgList : [{"attachmentFileUrl":"/rici/caiLiao?id=263&category=1","attachmentFileName":"yuba1582618588403.jpg"}]
     */

    private String caseTypeAudit;
    private String code;
    private String plaintiffCertificateNumber;
    private String breakLawOrg;
    private String source;
    private int caseType;
    private String feedback;
    private String plaintiffName;
    private String lastUpdated;
    private String score;
    private String dateCreated;
    private String plaintiffPhone;
    private int areaType;
    private String defendantAddress;
    private int id;
    private String isRepeat;
    private String picCreateTime;
    private String reply;
    private String isTrans;
    private String auditDate;
    private String breakLawOrgOther;
    private String unitName;
    private String replyDate;
    private String doOrg;
    private String defendantName;
    private String areaCity;
    private String exportDate;
    private String organization;
    private String areaProv;
    private String involveOrg;
    private String breakLawProperty;
    private String areaRemark;
    private String reflectRemark;
    private int harmRange;
    private String plaintiffAddress;
    private String smscontent;
    private String status;
    private List<ImgListBean> imgList;

    public String getCaseTypeAudit() {
        return caseTypeAudit;
    }

    public void setCaseTypeAudit(String caseTypeAudit) {
        this.caseTypeAudit = caseTypeAudit;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlaintiffCertificateNumber() {
        return plaintiffCertificateNumber;
    }

    public void setPlaintiffCertificateNumber(String plaintiffCertificateNumber) {
        this.plaintiffCertificateNumber = plaintiffCertificateNumber;
    }

    public String getBreakLawOrg() {
        return breakLawOrg;
    }

    public void setBreakLawOrg(String breakLawOrg) {
        this.breakLawOrg = breakLawOrg;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getCaseType() {
        return caseType;
    }

    public void setCaseType(int caseType) {
        this.caseType = caseType;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getPlaintiffName() {
        return plaintiffName;
    }

    public void setPlaintiffName(String plaintiffName) {
        this.plaintiffName = plaintiffName;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
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

    public String getPlaintiffPhone() {
        return plaintiffPhone;
    }

    public void setPlaintiffPhone(String plaintiffPhone) {
        this.plaintiffPhone = plaintiffPhone;
    }

    public int getAreaType() {
        return areaType;
    }

    public void setAreaType(int areaType) {
        this.areaType = areaType;
    }

    public String getDefendantAddress() {
        return defendantAddress;
    }

    public void setDefendantAddress(String defendantAddress) {
        this.defendantAddress = defendantAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }

    public String getPicCreateTime() {
        return picCreateTime;
    }

    public void setPicCreateTime(String picCreateTime) {
        this.picCreateTime = picCreateTime;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }


    public String getIsTrans() {
        return isTrans;
    }

    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getBreakLawOrgOther() {
        return breakLawOrgOther;
    }

    public void setBreakLawOrgOther(String breakLawOrgOther) {
        this.breakLawOrgOther = breakLawOrgOther;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getDoOrg() {
        return doOrg;
    }

    public void setDoOrg(String doOrg) {
        this.doOrg = doOrg;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getAreaCity() {
        return areaCity;
    }

    public void setAreaCity(String areaCity) {
        this.areaCity = areaCity;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getAreaProv() {
        return areaProv;
    }

    public void setAreaProv(String areaProv) {
        this.areaProv = areaProv;
    }

    public String getInvolveOrg() {
        return involveOrg;
    }

    public void setInvolveOrg(String involveOrg) {
        this.involveOrg = involveOrg;
    }

    public String getBreakLawProperty() {
        return breakLawProperty;
    }

    public void setBreakLawProperty(String breakLawProperty) {
        this.breakLawProperty = breakLawProperty;
    }

    public String getAreaRemark() {
        return areaRemark;
    }

    public void setAreaRemark(String areaRemark) {
        this.areaRemark = areaRemark;
    }

    public String getReflectRemark() {
        return reflectRemark;
    }

    public void setReflectRemark(String reflectRemark) {
        this.reflectRemark = reflectRemark;
    }

    public int getHarmRange() {
        return harmRange;
    }

    public void setHarmRange(int harmRange) {
        this.harmRange = harmRange;
    }

    public String getPlaintiffAddress() {
        return plaintiffAddress;
    }

    public void setPlaintiffAddress(String plaintiffAddress) {
        this.plaintiffAddress = plaintiffAddress;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public List<ImgListBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgListBean> imgList) {
        this.imgList = imgList;
    }


    public static class ImgListBean implements Serializable{
        /**
         * attachmentFileUrl : /rici/caiLiao?id=263&category=1
         * attachmentFileName : yuba1582618588403.jpg
         */

        private String attachmentFileUrl;
        private String attachmentFileName;

        public String getAttachmentFileUrl() {
            return attachmentFileUrl;
        }

        public void setAttachmentFileUrl(String attachmentFileUrl) {
            this.attachmentFileUrl = attachmentFileUrl;
        }

        public String getAttachmentFileName() {
            return attachmentFileName;
        }

        public void setAttachmentFileName(String attachmentFileName) {
            this.attachmentFileName = attachmentFileName;
        }
    }
}
