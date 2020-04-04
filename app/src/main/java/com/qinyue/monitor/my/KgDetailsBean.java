package com.qinyue.monitor.my;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/4
 * 描述:
 **/
public class KgDetailsBean implements Serializable {

    /**
     * plaintiffSex : 0
     * documentNumber : null
     * defendantRank : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":11062,"dm":"9914180800001","fdm":null,"lbbm":"9914","mc":"无职级","sfsc":"N","sm":null}
     * office : null
     * source : 小程序
     * referee : null
     * caseNature : null
     * feedback : null
     * lastUpdated : 2020-04-02T06:25:44Z
     * score : null
     * deliveryAddress : null
     * refereeDate : null
     * id : 93
     * defendantUintFullName : 啊啊啊
     * isRepeat : null
     * reply : null
     * petitionSource : 网上信访
     * isTrans : null
     * replyDate : null
     * agentName : null
     * court : null
     * plaintiffNation : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":7738,"dm":"9912180100001","fdm":null,"lbbm":"9912","mc":"汉族","sfsc":"N","sm":null}
     * defendantName : 李琪
     * plaintiffResidence : 呼和浩特
     * retrialDate : null
     * defendantNationality : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":7499,"dm":"9911180200002","fdm":null,"lbbm":"9911","mc":"中国香港","sfsc":"N","sm":null}
     * agentUnit : null
     * defendantUnitLocation : 香港
     * plaintiffEmail : 12038@qq.com
     * status : 待办理
     * imgList : [{"attachmentFileUrl":"/rici/caiLiao?id=256&category=1","attachmentFileName":"yuba1582618588403.jpg"},{"attachmentFileUrl":"/rici/caiLiao?id=257&category=2","attachmentFileName":"yuba1582618588403.jpg"},{"attachmentFileUrl":"/rici/caiLiao?id=258&category=3","attachmentFileName":"yuba1582618588403.jpg"}]
     * submitter : {"id":"402881f07119b17501711a9e413c0007","class":"com.bjrxkj.core.BaseUser"}
     * venue : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":8271,"dm":"9913000210102","fdm":"9913000210100","lbbm":"9913","mc":"辽宁省沈阳市和平区","sfsc":"N","sm":null}
     * code : null
     * court2 : null
     * corporationName : null
     * plaintiffCertificateNumber : 150105199610153612
     * defendantDuty : 雨
     * agentCertificateType : null
     * court3 : null
     * court4 : null
     * content : 内容啊
     * corporationDefendantDuty : null
     * defendantIdentity : null
     * plaintiffName : 卜
     * relationship2 : null
     * dateCreated : 2020-04-02T06:25:44Z
     * plaintiffPhone : 18804916183
     * plaintiffNationality : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":7498,"dm":"9911180200001","fdm":null,"lbbm":"9911","mc":"中国","sfsc":"N","sm":null}
     * relationship : null
     * class : com.bjrxkj.petition.AllPetition
     * plaintiffUnit : 单位
     * auditDate : null
     * agentCertificateNumber : null
     * deliveryAddress2 : null
     * defendantSex : 女
     * defendantNPC : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":11265,"dm":"9918181500003","fdm":null,"lbbm":"9918","mc":"县级","sfsc":"N","sm":null}
     * doOrg : null
     * documentNumber2 : null
     * documentNumber3 : null
     * documentNumber4 : null
     * documentNumber5 : null
     * exportDate : null
     * defendantUintType : null
     * organization : 北京市院
     * plaintiffCertificateType : {"class":"com.bjrxkj.petition.PetitionLetterDict","id":7488,"dm":"9910180300001","fdm":null,"lbbm":"9910","mc":"居民身份证","sfsc":"N","sm":null}
     * defendantCPPCC : null
     * petitionType : 1
     * referee4 : null
     * referee2 : null
     * refereeDate3 : null
     * referee3 : null
     * refereeDate2 : null
     * refereeDate4 : null
     * smscontent : null
     * toOrg : {"id":"cbe523dfd3e24b83b95616492c56940d","class":"com.bjrxkj.dictionary.Organization"}
     */

    private String plaintiffSex;
    private String documentNumber;
    private DeRankBean defendantRank;
    private String office;
    private String source;
    private String referee;
    private String caseNature;
    private String feedback;
    private String lastUpdated;
    private String score;
    private String deliveryAddress;
    private String refereeDate;
    private int id;
    private String defendantUintFullName;
    private String isRepeat;
    private String reply;
    private String petitionSource;
    private String isTrans;
    private String replyDate;
    private String agentName;
    private String court;
    private DeRankBean plaintiffNation;
    private String defendantName;
    private String plaintiffResidence;
    private String retrialDate;
    private DeRankBean defendantNationality;
    private String agentUnit;
    private String defendantUnitLocation;
    private String plaintiffEmail;
    private String status;
    private DeRankBean venue;
    private String code;
    private String court2;
    private String corporationName;
    private String plaintiffCertificateNumber;
    private String defendantDuty;
    private DeRankBean agentCertificateType;
    private String court3;
    private String court4;
    private String content;
    private String corporationDefendantDuty;
    private DeRankBean defendantIdentity;
    private String plaintiffName;
    private String relationship2;
    private String dateCreated;
    private String plaintiffPhone;
    private DeRankBean plaintiffNationality;
    private String relationship;
    private String plaintiffUnit;
    private String auditDate;
    private String agentCertificateNumber;
    private String deliveryAddress2;
    private String defendantSex;
    private DeRankBean defendantNPC;
    private String doOrg;
    private String documentNumber2;
    private String documentNumber3;
    private String documentNumber4;
    private String documentNumber5;
    private String exportDate;
    private String defendantUintType;
    private String organization;
    private DeRankBean plaintiffCertificateType;
    private String defendantCPPCC;
    private int petitionType;
    private String referee4;
    private String referee2;
    private String refereeDate3;
    private String referee3;
    private String refereeDate2;
    private String refereeDate4;
    private String smscontent;

    private List<GyssDetailsBean.ImgListBean> imgList;

    public String getPlaintiffSex() {
        return plaintiffSex;
    }

    public void setPlaintiffSex(String plaintiffSex) {
        this.plaintiffSex = plaintiffSex;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public DeRankBean getDefendantRank() {
        return defendantRank;
    }

    public void setDefendantRank(DeRankBean defendantRank) {
        this.defendantRank = defendantRank;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getCaseNature() {
        return caseNature;
    }

    public void setCaseNature(String caseNature) {
        this.caseNature = caseNature;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getRefereeDate() {
        return refereeDate;
    }

    public void setRefereeDate(String refereeDate) {
        this.refereeDate = refereeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefendantUintFullName() {
        return defendantUintFullName;
    }

    public void setDefendantUintFullName(String defendantUintFullName) {
        this.defendantUintFullName = defendantUintFullName;
    }

    public String getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getPetitionSource() {
        return petitionSource;
    }

    public void setPetitionSource(String petitionSource) {
        this.petitionSource = petitionSource;
    }

    public String getIsTrans() {
        return isTrans;
    }

    public void setIsTrans(String isTrans) {
        this.isTrans = isTrans;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public DeRankBean getPlaintiffNation() {
        return plaintiffNation;
    }

    public void setPlaintiffNation(DeRankBean plaintiffNation) {
        this.plaintiffNation = plaintiffNation;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getPlaintiffResidence() {
        return plaintiffResidence;
    }

    public void setPlaintiffResidence(String plaintiffResidence) {
        this.plaintiffResidence = plaintiffResidence;
    }

    public String getRetrialDate() {
        return retrialDate;
    }

    public void setRetrialDate(String retrialDate) {
        this.retrialDate = retrialDate;
    }

    public DeRankBean getDefendantNationality() {
        return defendantNationality;
    }

    public void setDefendantNationality(DeRankBean defendantNationality) {
        this.defendantNationality = defendantNationality;
    }

    public String getAgentUnit() {
        return agentUnit;
    }

    public void setAgentUnit(String agentUnit) {
        this.agentUnit = agentUnit;
    }

    public String getDefendantUnitLocation() {
        return defendantUnitLocation;
    }

    public void setDefendantUnitLocation(String defendantUnitLocation) {
        this.defendantUnitLocation = defendantUnitLocation;
    }

    public String getPlaintiffEmail() {
        return plaintiffEmail;
    }

    public void setPlaintiffEmail(String plaintiffEmail) {
        this.plaintiffEmail = plaintiffEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeRankBean getVenue() {
        return venue;
    }

    public void setVenue(DeRankBean venue) {
        this.venue = venue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourt2() {
        return court2;
    }

    public void setCourt2(String court2) {
        this.court2 = court2;
    }

    public String getCorporationName() {
        return corporationName;
    }

    public void setCorporationName(String corporationName) {
        this.corporationName = corporationName;
    }

    public String getPlaintiffCertificateNumber() {
        return plaintiffCertificateNumber;
    }

    public void setPlaintiffCertificateNumber(String plaintiffCertificateNumber) {
        this.plaintiffCertificateNumber = plaintiffCertificateNumber;
    }

    public String getDefendantDuty() {
        return defendantDuty;
    }

    public void setDefendantDuty(String defendantDuty) {
        this.defendantDuty = defendantDuty;
    }

    public DeRankBean getAgentCertificateType() {
        return agentCertificateType;
    }

    public void setAgentCertificateType(DeRankBean agentCertificateType) {
        this.agentCertificateType = agentCertificateType;
    }

    public String getCourt3() {
        return court3;
    }

    public void setCourt3(String court3) {
        this.court3 = court3;
    }

    public String getCourt4() {
        return court4;
    }

    public void setCourt4(String court4) {
        this.court4 = court4;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCorporationDefendantDuty() {
        return corporationDefendantDuty;
    }

    public void setCorporationDefendantDuty(String corporationDefendantDuty) {
        this.corporationDefendantDuty = corporationDefendantDuty;
    }

    public DeRankBean getDefendantIdentity() {
        return defendantIdentity;
    }

    public void setDefendantIdentity(DeRankBean defendantIdentity) {
        this.defendantIdentity = defendantIdentity;
    }

    public String getPlaintiffName() {
        return plaintiffName;
    }

    public void setPlaintiffName(String plaintiffName) {
        this.plaintiffName = plaintiffName;
    }

    public String getRelationship2() {
        return relationship2;
    }

    public void setRelationship2(String relationship2) {
        this.relationship2 = relationship2;
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

    public DeRankBean getPlaintiffNationality() {
        return plaintiffNationality;
    }

    public void setPlaintiffNationality(DeRankBean plaintiffNationality) {
        this.plaintiffNationality = plaintiffNationality;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getPlaintiffUnit() {
        return plaintiffUnit;
    }

    public void setPlaintiffUnit(String plaintiffUnit) {
        this.plaintiffUnit = plaintiffUnit;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAgentCertificateNumber() {
        return agentCertificateNumber;
    }

    public void setAgentCertificateNumber(String agentCertificateNumber) {
        this.agentCertificateNumber = agentCertificateNumber;
    }

    public String getDeliveryAddress2() {
        return deliveryAddress2;
    }

    public void setDeliveryAddress2(String deliveryAddress2) {
        this.deliveryAddress2 = deliveryAddress2;
    }

    public String getDefendantSex() {
        return defendantSex;
    }

    public void setDefendantSex(String defendantSex) {
        this.defendantSex = defendantSex;
    }

    public DeRankBean getDefendantNPC() {
        return defendantNPC;
    }

    public void setDefendantNPC(DeRankBean defendantNPC) {
        this.defendantNPC = defendantNPC;
    }

    public String getDoOrg() {
        return doOrg;
    }

    public void setDoOrg(String doOrg) {
        this.doOrg = doOrg;
    }

    public String getDocumentNumber2() {
        return documentNumber2;
    }

    public void setDocumentNumber2(String documentNumber2) {
        this.documentNumber2 = documentNumber2;
    }

    public String getDocumentNumber3() {
        return documentNumber3;
    }

    public void setDocumentNumber3(String documentNumber3) {
        this.documentNumber3 = documentNumber3;
    }

    public String getDocumentNumber4() {
        return documentNumber4;
    }

    public void setDocumentNumber4(String documentNumber4) {
        this.documentNumber4 = documentNumber4;
    }

    public String getDocumentNumber5() {
        return documentNumber5;
    }

    public void setDocumentNumber5(String documentNumber5) {
        this.documentNumber5 = documentNumber5;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getDefendantUintType() {
        return defendantUintType;
    }

    public void setDefendantUintType(String defendantUintType) {
        this.defendantUintType = defendantUintType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public DeRankBean getPlaintiffCertificateType() {
        return plaintiffCertificateType;
    }

    public void setPlaintiffCertificateType(DeRankBean plaintiffCertificateType) {
        this.plaintiffCertificateType = plaintiffCertificateType;
    }

    public String getDefendantCPPCC() {
        return defendantCPPCC;
    }

    public void setDefendantCPPCC(String defendantCPPCC) {
        this.defendantCPPCC = defendantCPPCC;
    }

    public int getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(int petitionType) {
        this.petitionType = petitionType;
    }

    public String getReferee4() {
        return referee4;
    }

    public void setReferee4(String referee4) {
        this.referee4 = referee4;
    }

    public String getReferee2() {
        return referee2;
    }

    public void setReferee2(String referee2) {
        this.referee2 = referee2;
    }

    public String getRefereeDate3() {
        return refereeDate3;
    }

    public void setRefereeDate3(String refereeDate3) {
        this.refereeDate3 = refereeDate3;
    }

    public String getReferee3() {
        return referee3;
    }

    public void setReferee3(String referee3) {
        this.referee3 = referee3;
    }

    public String getRefereeDate2() {
        return refereeDate2;
    }

    public void setRefereeDate2(String refereeDate2) {
        this.refereeDate2 = refereeDate2;
    }

    public String getRefereeDate4() {
        return refereeDate4;
    }

    public void setRefereeDate4(String refereeDate4) {
        this.refereeDate4 = refereeDate4;
    }

    public String getSmscontent() {
        return smscontent;
    }

    public void setSmscontent(String smscontent) {
        this.smscontent = smscontent;
    }

    public List<GyssDetailsBean.ImgListBean> getImgList() {
        return imgList;
    }

    public void setImgList(List<GyssDetailsBean.ImgListBean> imgList) {
        this.imgList = imgList;
    }
}
