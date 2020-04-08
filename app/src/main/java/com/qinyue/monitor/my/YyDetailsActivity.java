package com.qinyue.monitor.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xui.widget.toast.XToast;
import com.xuexiang.xutil.data.DateUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/4/3
 * 描述:
 **/
public class YyDetailsActivity extends BaseActivity {
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.zjlx_tv)
    TextView zjlxTv;
    @BindView(R.id.zjhm_tv)
    TextView zjhmTv;
    @BindView(R.id.mz_tv)
    TextView mzTv;
    @BindView(R.id.gzdw_tv)
    TextView gzdwTv;
    @BindView(R.id.lxdh_tv)
    TextView lxdhTv;
    @BindView(R.id.sex_tv)
    TextView sexTv;
    @BindView(R.id.gj_tv)
    TextView gjTv;
    @BindView(R.id.jsd_tv)
    TextView jsdTv;
    @BindView(R.id.email_tv)
    TextView emailTv;
    @BindView(R.id.ajlb_tv)
    TextView ajlbTv;
    @BindView(R.id.nrzy_tv)
    TextView nrzyTv;
    @BindView(R.id.time_tv)
    TextView timeTv;
    @BindView(R.id.zt_tv)
    TextView ztTv;
    @BindView(R.id.shjg_tv)
    TextView shjgTv;
    @BindView(R.id.dfnr_tv)
    TextView dfnrTv;
    @BindView(R.id.jg_lin)
    LinearLayout jgLin;
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;


    private String title = "";
    private String id = "";

    @Override
    public String initTitleText() {
        return title;
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (id==null){
            llStateful.showEmpty("内容丢失");
        }else {
            llStateful.showLoading();
            getDetails();
        }

    }

    @Override
    protected void initview() {
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_yydetails;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void getDetails(){
        Map<String,String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("id", id);
        String json = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY,json);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.appointmentDetail)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data",aes)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult()==200){
                        String aa = Base64Converter.AESDncode(TagConstant.AESKEY,s.getData());
                        YyDetailsBean yyDetailsBean = JsonUtils.getInstance().gson.fromJson(aa, YyDetailsBean.class);
                        name.setText(yyDetailsBean.getName());
                        zjlxTv.setText(yyDetailsBean.getCardType());
                        zjhmTv.setText(yyDetailsBean.getCardCode());
                        mzTv.setText(yyDetailsBean.getNation());
                        gzdwTv.setText(yyDetailsBean.getOrgunit());
                        lxdhTv.setText(yyDetailsBean.getPhone());
                        sexTv.setText(yyDetailsBean.getGender());
                        gjTv.setText(yyDetailsBean.getNationality());
                        emailTv.setText(yyDetailsBean.getEmail());
                        ajlbTv.setText(yyDetailsBean.getCaseType());
                        nrzyTv.setText(yyDetailsBean.getContent());
                        jsdTv.setText(yyDetailsBean.getHome());
                        if (yyDetailsBean.getDateCreated()!=null&&yyDetailsBean.getDateCreated().contains("T")){
                            String[] ts = yyDetailsBean.getDateCreated().split("T");
                            if (ts.length>1){
                                timeTv.setText(ts[0]);
                            }
                        }
                        ztTv.setText(yyDetailsBean.getStatus());
                        if ("已办理".equals(yyDetailsBean.getStatus())){
                            jgLin.setVisibility(View.VISIBLE);
                            shjgTv.setText(yyDetailsBean.getIsaudit()?"审核通过":"审核不通过");
                            dfnrTv.setText(yyDetailsBean.getReply());
                        }

                        llStateful.showContent();
                    }else {
                        llStateful.showError(s.getMessage(),new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llStateful.showLoading();
                                getDetails();
                            }
                        });
                    }
                }, throwable -> {
                    llStateful.showError(throwable.getMessage(),new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            llStateful.showLoading();
                            getDetails();
                        }
                    });
                });
    }

}
