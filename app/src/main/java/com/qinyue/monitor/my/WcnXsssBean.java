package com.qinyue.monitor.my;

import java.io.Serializable;
import java.util.List;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/4
 * 描述:
 **/
public class WcnXsssBean implements Serializable {

    /**
     * plaintiffSex : 男
     * caseOrgName : 哦哦哦
     * plaintiffDuty : 快快快
     * juvenileBirthday : 2020-04-03T16:00:00Z
     * plaintiffAreaid : null
     * juvenileSex : 男
     * caseAreaid : null
     * source : 小程序
     * juvenileNationality : 9911180200028
     * caseNature : null
     * defendantDomicile : 北京市东城区
     * feedback : null
     * defendantOtherIdentity : 13
     * lastUpdated : 2020-04-03T18:30:34Z
     * score : null
     * juvenileResidenceid : null
     * defendantAddress : 略略略
     * id : 14
     * isRepeat : null
     * reply : null
     * isTrans : null
     * juvenileAgent : III
     * defendantUnit : 久
     * decReason : null
     * replyDate : null
     * plaintiffNation : 9912180100026
     * juvenileAge : 不满12周岁
     * caseOrgSrcName : null
     * caseOrgType : 公安机关
     * defendantName : III
     * accuseOtherReason : null
     * plaintiffResidence : 天津市河东区
     * juvenileResidence : 辽宁省沈阳市和平区
     * status : 待办理
     * imgList : []
     * submitter : {"id":"402881f07119b17501711a9e413c0007","class":"com.bjrxkj.core.BaseUser"}
     * caseDate : 2020-04-05T16:00:00Z
     * code : null
     * juvenileName : ii
     * juvenileDomicile : +8613074797395
     * plaintiffIdentity : 1
     * juvenileCertificateType : 9910180300001
     * plaintiffCertificateNumber : 140108199807124214
     * defendantDuty : 姐姐家
     * salveeType : null
     * juvenileNameBefore : ii
     * juvenileSchoolAddressid : null
     * juvenileSchool : uuu
     * juvenileGuardState : 其他成年家属
     * content : 哈哈
     * caseType : null
     * defendantIdentity : 133
     * plaintiffName : 谢立秋
     * caseRemark : null
     * dateCreated : 2020-04-03T18:30:34Z
     * juvenilePosition : 被害人
     * appealType : 不服不批捕
     * plaintiffPhone : +8613074797395
     * plaintiffArea : 天津市和平区
     * juvenileDomicileid : null
     * plaintiffNationality : 9911180200026
     * relationship : 未成年人本人
     * class : com.bjrxkj.juvenile.Juvenile
     * auditDate : null
     * caseArea : 北京市西城区
     * defendantUnitNature : null
     * juvenileSchoolAddress : 河北省石家庄市长安区
     * defendantSex : 男
     * juvenileCertificateNumber : 152634199512032412
     * doOrg : null
     * caseReason : 强制猥琐、侮辱罪
     * defendantDomicileid : null
     * exportDate : null
     * juvenileNickname : 快快快
     * helpType : null
     * organization : 北京市院
     * plaintiffCertificateType : 居民身份证
     * juvenileAddress : i看看
     * juvenileNation : 9912180100038
     * petitionType : 2
     * smscontent : null
     * toOrg : {"id":"cbe523dfd3e24b83b95616492c56940d","class":"com.bjrxkj.dictionary.Organization"}
     */

    private String plaintiffSex;
    private String caseOrgName;
    private String plaintiffDuty;
    private String juvenileBirthday;
    private String plaintiffAreaid;
    private String juvenileSex;
    private String caseAreaid;
    private String source;
    private String juvenileNationality;
    private String caseNature;
    private String defendantDomicile;
    private String feedback;
    private String defendantOtherIdentity;
    private String lastUpdated;
    private String score;
    private String juvenileResidenceid;
    private String defendantAddress;
    private int id;
    private String isRepeat;
    private String reply;
    private String isTrans;
    private String juvenileAgent;
    private String defendantUnit;
    private String decReason;
    private String replyDate;
    private String plaintiffNation;
    private String juvenileAge;
    private String caseOrgSrcName;
    private String caseOrgType;
    private String defendantName;
    private String accuseOtherReason;
    private String plaintiffResidence;
    private String juvenileResidence;
    private String status;
    private String caseDate;
    private String code;
    private String juvenileName;
    private String juvenileDomicile;
    private String plaintiffIdentity;
    private String juvenileCertificateType;
    private String plaintiffCertificateNumber;
    private String defendantDuty;
    private String salveeType;
    private String juvenileNameBefore;
    private String juvenileSchoolAddressid;
    private String juvenileSchool;
    private String juvenileGuardState;
    private String content;
    private String caseType;
    private String defendantIdentity;
    private String plaintiffName;
    private String caseRemark;
    private String dateCreated;
    private String juvenilePosition;
    private String appealType;
    private String plaintiffPhone;
    private String plaintiffArea;
    private String juvenileDomicileid;
    private String plaintiffNationality;
    private String relationship;
    private String auditDate;
    private String caseArea;
    private String defendantUnitNature;
    private String juvenileSchoolAddress;
    private String defendantSex;
    private String juvenileCertificateNumber;
    private String doOrg;
    private String caseReason;
    private String defendantDomicileid;
    private String exportDate;
    private String juvenileNickname;
    private String helpType;
    private String organization;
    private String plaintiffCertificateType;
    private String juvenileAddress;
    private String juvenileNation;
    private int petitionType;
    private String smscontent;
    private java.util.List<GyssDetailsBean.ImgListBean> imgList;

    public String getPlaintiffSex() {
        return plaintiffSex;
    }

    public void setPlaintiffSex(String plaintiffSex) {
        this.plaintiffSex = plaintiffSex;
    }

    public String getCaseOrgName() {
        return caseOrgName;
    }

    public void setCaseOrgName(String caseOrgName) {
        this.caseOrgName = caseOrgName;
    }

    public String getPlaintiffDuty() {
        return plaintiffDuty;
    }

    public void setPlaintiffDuty(String plaintiffDuty) {
        this.plaintiffDuty = plaintiffDuty;
    }

    public String getJuvenileBirthday() {
        return juvenileBirthday;
    }

    public void setJuvenileBirthday(String juvenileBirthday) {
        this.juvenileBirthday = juvenileBirthday;
    }

    public String getPlaintiffAreaid() {
        return plaintiffAreaid;
    }

    public void setPlaintiffAreaid(String plaintiffAreaid) {
        this.plaintiffAreaid = plaintiffAreaid;
    }

    public String getJuvenileSex() {
        return juvenileSex;
    }

    public void setJuvenileSex(String juvenileSex) {
        this.juvenileSex = juvenileSex;
    }

    public String getCaseAreaid() {
        return caseAreaid;
    }

    public void setCaseAreaid(String caseAreaid) {
        this.caseAreaid = caseAreaid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getJuvenileNationality() {
        return juvenileNationality;
    }

    public void setJuvenileNationality(String juvenileNationality) {
        this.juvenileNationality = juvenileNationality;
    }

    public String getCaseNature() {
        return caseNature;
    }

    public void setCaseNature(String caseNature) {
        this.caseNature = caseNature;
    }

    public String getDefendantDomicile() {
        return defendantDomicile;
    }

    public void setDefendantDomicile(String defendantDomicile) {
        this.defendantDomicile = defendantDomicile;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDefendantOtherIdentity() {
        return defendantOtherIdentity;
    }

    public void setDefendantOtherIdentity(String defendantOtherIdentity) {
        this.defendantOtherIdentity = defendantOtherIdentity;
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

    public String getJuvenileResidenceid() {
        return juvenileResidenceid;
    }

    public void setJuvenileResidenceid(String juvenileResidenceid) {
        this.juvenileResidenceid = juvenileResidenceid;
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

    public String getJuvenileAgent() {
        return juvenileAgent;
    }

    public void setJuvenileAgent(String juvenileAgent) {
        this.juvenileAgent = juvenileAgent;
    }

    public String getDefendantUnit() {
        return defendantUnit;
    }

    public void setDefendantUnit(String defendantUnit) {
        this.defendantUnit = defendantUnit;
    }

    public String getDecReason() {
        return decReason;
    }

    public void setDecReason(String decReason) {
        this.decReason = decReason;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public String getPlaintiffNation() {
        return plaintiffNation;
    }

    public void setPlaintiffNation(String plaintiffNation) {
        this.plaintiffNation = plaintiffNation;
    }

    public String getJuvenileAge() {
        return juvenileAge;
    }

    public void setJuvenileAge(String juvenileAge) {
        this.juvenileAge = juvenileAge;
    }

    public String getCaseOrgSrcName() {
        return caseOrgSrcName;
    }

    public void setCaseOrgSrcName(String caseOrgSrcName) {
        this.caseOrgSrcName = caseOrgSrcName;
    }

    public String getCaseOrgType() {
        return caseOrgType;
    }

    public void setCaseOrgType(String caseOrgType) {
        this.caseOrgType = caseOrgType;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getAccuseOtherReason() {
        return accuseOtherReason;
    }

    public void setAccuseOtherReason(String accuseOtherReason) {
        this.accuseOtherReason = accuseOtherReason;
    }

    public String getPlaintiffResidence() {
        return plaintiffResidence;
    }

    public void setPlaintiffResidence(String plaintiffResidence) {
        this.plaintiffResidence = plaintiffResidence;
    }

    public String getJuvenileResidence() {
        return juvenileResidence;
    }

    public void setJuvenileResidence(String juvenileResidence) {
        this.juvenileResidence = juvenileResidence;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJuvenileName() {
        return juvenileName;
    }

    public void setJuvenileName(String juvenileName) {
        this.juvenileName = juvenileName;
    }

    public String getJuvenileDomicile() {
        return juvenileDomicile;
    }

    public void setJuvenileDomicile(String juvenileDomicile) {
        this.juvenileDomicile = juvenileDomicile;
    }

    public String getPlaintiffIdentity() {
        return plaintiffIdentity;
    }

    public void setPlaintiffIdentity(String plaintiffIdentity) {
        this.plaintiffIdentity = plaintiffIdentity;
    }

    public String getJuvenileCertificateType() {
        return juvenileCertificateType;
    }

    public void setJuvenileCertificateType(String juvenileCertificateType) {
        this.juvenileCertificateType = juvenileCertificateType;
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

    public String getSalveeType() {
        return salveeType;
    }

    public void setSalveeType(String salveeType) {
        this.salveeType = salveeType;
    }

    public String getJuvenileNameBefore() {
        return juvenileNameBefore;
    }

    public void setJuvenileNameBefore(String juvenileNameBefore) {
        this.juvenileNameBefore = juvenileNameBefore;
    }

    public String getJuvenileSchoolAddressid() {
        return juvenileSchoolAddressid;
    }

    public void setJuvenileSchoolAddressid(String juvenileSchoolAddressid) {
        this.juvenileSchoolAddressid = juvenileSchoolAddressid;
    }

    public String getJuvenileSchool() {
        return juvenileSchool;
    }

    public void setJuvenileSchool(String juvenileSchool) {
        this.juvenileSchool = juvenileSchool;
    }

    public String getJuvenileGuardState() {
        return juvenileGuardState;
    }

    public void setJuvenileGuardState(String juvenileGuardState) {
        this.juvenileGuardState = juvenileGuardState;
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

    public String getDefendantIdentity() {
        return defendantIdentity;
    }

    public void setDefendantIdentity(String defendantIdentity) {
        this.defendantIdentity = defendantIdentity;
    }

    public String getPlaintiffName() {
        return plaintiffName;
    }

    public void setPlaintiffName(String plaintiffName) {
        this.plaintiffName = plaintiffName;
    }

    public String getCaseRemark() {
        return caseRemark;
    }

    public void setCaseRemark(String caseRemark) {
        this.caseRemark = caseRemark;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getJuvenilePosition() {
        return juvenilePosition;
    }

    public void setJuvenilePosition(String juvenilePosition) {
        this.juvenilePosition = juvenilePosition;
    }

    public String getAppealType() {
        return appealType;
    }

    public void setAppealType(String appealType) {
        this.appealType = appealType;
    }

    public String getPlaintiffPhone() {
        return plaintiffPhone;
    }

    public void setPlaintiffPhone(String plaintiffPhone) {
        this.plaintiffPhone = plaintiffPhone;
    }

    public String getPlaintiffArea() {
        return plaintiffArea;
    }

    public void setPlaintiffArea(String plaintiffArea) {
        this.plaintiffArea = plaintiffArea;
    }

    public String getJuvenileDomicileid() {
        return juvenileDomicileid;
    }

    public void setJuvenileDomicileid(String juvenileDomicileid) {
        this.juvenileDomicileid = juvenileDomicileid;
    }

    public String getPlaintiffNationality() {
        return plaintiffNationality;
    }

    public void setPlaintiffNationality(String plaintiffNationality) {
        this.plaintiffNationality = plaintiffNationality;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getCaseArea() {
        return caseArea;
    }

    public void setCaseArea(String caseArea) {
        this.caseArea = caseArea;
    }

    public String getDefendantUnitNature() {
        return defendantUnitNature;
    }

    public void setDefendantUnitNature(String defendantUnitNature) {
        this.defendantUnitNature = defendantUnitNature;
    }

    public String getJuvenileSchoolAddress() {
        return juvenileSchoolAddress;
    }

    public void setJuvenileSchoolAddress(String juvenileSchoolAddress) {
        this.juvenileSchoolAddress = juvenileSchoolAddress;
    }

    public String getDefendantSex() {
        return defendantSex;
    }

    public void setDefendantSex(String defendantSex) {
        this.defendantSex = defendantSex;
    }

    public String getJuvenileCertificateNumber() {
        return juvenileCertificateNumber;
    }

    public void setJuvenileCertificateNumber(String juvenileCertificateNumber) {
        this.juvenileCertificateNumber = juvenileCertificateNumber;
    }

    public String getDoOrg() {
        return doOrg;
    }

    public void setDoOrg(String doOrg) {
        this.doOrg = doOrg;
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason;
    }

    public String getDefendantDomicileid() {
        return defendantDomicileid;
    }

    public void setDefendantDomicileid(String defendantDomicileid) {
        this.defendantDomicileid = defendantDomicileid;
    }

    public String getExportDate() {
        return exportDate;
    }

    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

    public String getJuvenileNickname() {
        return juvenileNickname;
    }

    public void setJuvenileNickname(String juvenileNickname) {
        this.juvenileNickname = juvenileNickname;
    }

    public String getHelpType() {
        return helpType;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPlaintiffCertificateType() {
        return plaintiffCertificateType;
    }

    public void setPlaintiffCertificateType(String plaintiffCertificateType) {
        this.plaintiffCertificateType = plaintiffCertificateType;
    }

    public String getJuvenileAddress() {
        return juvenileAddress;
    }

    public void setJuvenileAddress(String juvenileAddress) {
        this.juvenileAddress = juvenileAddress;
    }

    public String getJuvenileNation() {
        return juvenileNation;
    }

    public void setJuvenileNation(String juvenileNation) {
        this.juvenileNation = juvenileNation;
    }

    public int getPetitionType() {
        return petitionType;
    }

    public void setPetitionType(int petitionType) {
        this.petitionType = petitionType;
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
