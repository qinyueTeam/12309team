package com.qinyue.monitor.my;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.xuexiang.xui.widget.toast.XToast;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/30
 * 描述:
 **/
public class QzyjDetailsActivity extends BaseActivity {
    @BindView(R.id.name)
    TextView nameTv;
    @BindView(R.id.phone)
    TextView phoneTv;
    @BindView(R.id.type)
    TextView typeTv;
    @BindView(R.id.jg)
    TextView jgTv;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.content)
    TextView contentTv;
    @BindView(R.id.fin)
    TextView finTv;
    @BindView(R.id.ll_stateful)
    StatefulLayout statefulLayout;
    @BindView(R.id.lin_result)
    LinearLayout resultLin;
    @BindView(R.id.lin_result_string)
    LinearLayout resultStringLin;
    String id;

    @Override
    public String initTitleText() {
        return "意见建议详情";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        id = getIntent().getStringExtra("id");
        if (id == null) {
            XToast.error(this, "未知错误").show();
            finish();
        }
        getData();
//        nameTv.setText(jczxxMsgBean.getWriter());
//        phoneTv.setText(jczxxMsgBean.getPhone());
//        contentTv.setText(jczxxMsgBean.getContent());
//        timeTv.setText(jczxxMsgBean.getDateCreated());
//        stateTv.setText(jczxxMsgBean.getStatus());
    }

    @Override
    protected void initview() {
        statefulLayout.showLoading();
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_qzyjderails;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void getData() {
        Map<String, String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("id", id);
        String a = Base64Converter.AESEncode(TagConstant.AESKEY, JsonUtils.getInstance().gson.toJson(map));
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.suggestionDetail)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", a)
                .asObject(BaseResBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.getResult() == 200) {
                        String data = Base64Converter.AESDncode(TagConstant.AESKEY, s.getData());
                        QzyjBean qzyjBean = JsonUtils.getInstance().gson.fromJson(data, QzyjBean.class);
                        if ("未办理".equals(qzyjBean.getStatus())) {
                            resultLin.setVisibility(View.GONE);
                            resultStringLin.setVisibility(View.GONE);
                        } else {
                            finTv.setText(qzyjBean.getResult());
                        }
                        phoneTv.setText(UserUtils.getUserName());
                        nameTv.setText(UserUtils.getRealName());
                        jgTv.setText(qzyjBean.getToOrg());
                        typeTv.setText(qzyjBean.getType());
                        titleTv.setText(qzyjBean.getTitle());
                        contentTv.setText(qzyjBean.getContent());
                        statefulLayout.showContent();
                    } else {
                        statefulLayout.showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getData();
                            }
                        });
                    }
                }, throwable -> {
                    statefulLayout.showError(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getData();
                        }
                    });
                });
    }
}
