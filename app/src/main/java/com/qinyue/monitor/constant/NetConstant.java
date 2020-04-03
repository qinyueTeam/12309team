package com.qinyue.monitor.constant;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/26
 * 描述:
 **/
public class NetConstant {
    //根据用户名和密码登录
    public static final String loginByPwd = "/rici/loginByPassword";
    //获取用户信息
    public static final String getMyInfo = "/rici/myInfo";
    //获取验证码
    public static final String getCode = "/rici/sendSmsCode";
    //用户注册
    public static final String saveUser = "/rici/saveUser";
    //修改用户信息
    public static final String saveInfo = "/rici/saveInfo";
    //信访列表
    public static final String petitionList = "/rici/petitionList";
    //我的意见建议列表
    public static final String suggestionList = "/rici/suggestionList";
    //预约接访列表
    public static final String appointmentList = "/rici/appointmentList";
    //新闻/法律法规列表,须知
    public static final String newsList = "/announceShow/show";
    //重要案件信息列表
    public static final String caseList = "/rici/caseList";
    //法律文书列表
    public static final String lawDocList = "/rici/lawDocList";
    //案件详情
    public static final String caseDetail = "/rici/caseDetail";
    //文书详情
    public static final String lawDocDetail = "/rici/lawDocDetail";
    //检察长信箱列表/详情
    public static final String letterShow = "/announceShow/letterShow";
    //附件上传
    public static final String fileUpload = "/rici/fileUpload";
    //检察长信箱提交
    public static final String saveLetter = "/announceShow/saveLetter";
    //检察长信箱附件上传
    public static final String savePicture = "/announceShow/savePicture";
    //获取类型代码表
    public static final String getTypeCode = "/rici/code";
    //群众意见建议提交
    public static final String suggestion = "/rici/suggestion";
    //群众意见建议详情
    public static final String suggestionDetail = "/rici/suggestionDetail";
    //举报
    public static final String savejb = "/rici/savejb";
    //预约视频接访
    public static final String YYSPJF = "/rici/appointment";
    //查找当前身份证号关联了多少个手机号
    public static final String findPhoneByIdCard = "/rici/findPhoneByIdCard";
    //身份证号登录
    public static final String loginByIdCard = "/rici/loginByIdCard";
    //控告提交
    public static final String accuse = "/rici/accuse";
    //刑事诉讼
    public static final String criminalAppeal = "/rici/criminalAppeal";
    //国家赔偿
    public static final String stateCompensation = "/rici/stateCompensation";
    //其他信访
    public static final String otherPetition = "/rici/otherPetition";
    //未成年人控告
    public static final String juvenileAccuse = "/rici/juvenileAccuse";
    //未成年人刑事申诉
    public static final String juvenileCriminal = "/rici/juvenileCriminal";
    //未成年人申请救助
    public static final String juvenileRescue = "/rici/juvenileRescue";
}
