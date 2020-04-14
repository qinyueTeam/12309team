package com.qinyue.monitor.my;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseDataBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.first.AddJczxxActivity;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.GlideEngine;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.xuexiang.xui.widget.statelayout.StatefulLayout;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.Serializable;
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
public class JczxxDetailsActivity extends BaseActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.name)
    TextView nameTv;
    @BindView(R.id.phone)
    TextView phoneTv;
    @BindView(R.id.time)
    TextView timeTv;
    @BindView(R.id.content)
    TextView contentTv;
    @BindView(R.id.state)
    TextView stateTv;
    JczxxMsgBean jczxxMsgBean;
    CommonAdapter<FileBean> commonAdapter;
    private List<LocalMedia> mSelectList = new ArrayList<>();
    @BindView(R.id.ll_stateful)
    StatefulLayout llStateful;
    @Override
    public String initTitleText() {
        return "检察长信箱详情";
    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        jczxxMsgBean = (JczxxMsgBean) getIntent().getSerializableExtra("data");
        if (jczxxMsgBean == null) {
            llStateful.showEmpty("内容丢失");
            return;
        }else {
            llStateful.showLoading();
            getDetails();
        }
    }

    @Override
    protected void initview() {

    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_jczxxderails;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    private void getDetails() {
        Map<String, String> map = new HashMap<>();
        map.put("username", UserUtils.getUserName());
        map.put("id", jczxxMsgBean.getId());
        map.put("type", "1");
        map.put("petitionType", "");
        String json = JsonUtils.getInstance().gson.toJson(map);
        String aes = Base64Converter.AESEncode(TagConstant.AESKEY, json);
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL2 + NetConstant.letterShow)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aes)
                .asObject(BaseDataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (s.isResult()) {
                        String aa = Base64Converter.AESDncode(TagConstant.AESKEY, s.getData()+"");
                        jczxxMsgBean = JsonUtils.getInstance().gson.fromJson(aa, JczxxMsgBean.class);
                        if (jczxxMsgBean.getFiledList() != null) {
                            recyclerView.setLayoutManager(new LinearLayoutManager(this));
                            //添加自定义分割线
                            DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
                            divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.shape_transparent_divider));
                            recyclerView.addItemDecoration(divider);
                            commonAdapter = new CommonAdapter<FileBean>(this, R.layout.item_jczxxdetails, jczxxMsgBean.getFiledList()) {
                                @Override
                                protected void convert(ViewHolder holder, FileBean fileBean, int position) {
                                    holder.setText(R.id.name, fileBean.getFieldNames());
                                }
                            };
                            commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, RecyclerView.ViewHolder viewHolder, int position) {
                                    if (mSelectList.size() == 0) {
                                        for (int i = 0; i < jczxxMsgBean.getFiledList().size(); i++) {
                                            LocalMedia localMedia = new LocalMedia();
                                            localMedia.setFileName(jczxxMsgBean.getFiledList().get(i).getFieldNames());
                                            localMedia.setPath(TagConstant.BASEURL2+jczxxMsgBean.getFiledList().get(i).getFieldUrls());
                                            mSelectList.add(localMedia);
                                        }
                                    }
                                    PictureSelector.create(JczxxDetailsActivity.this).themeStyle(R.style.picture_default_style)
                                            .loadImageEngine(GlideEngine.createGlideEngine())
                                            .openExternalPreview(position, mSelectList);
                                }

                                @Override
                                public boolean onItemLongClick(View view, RecyclerView.ViewHolder viewHolder, int i) {
                                    return false;
                                }
                            });
                            recyclerView.setAdapter(commonAdapter);
                        }
                        nameTv.setText(jczxxMsgBean.getWriter());
                        phoneTv.setText(jczxxMsgBean.getPhone());
                        contentTv.setText(jczxxMsgBean.getContent());
                        timeTv.setText(jczxxMsgBean.getDateCreated());
                        stateTv.setText(jczxxMsgBean.getStatus());
                        llStateful.showContent();
                    } else {
                        llStateful.showError(s.getMsg(), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                llStateful.showLoading();
                                getDetails();
                            }
                        });
                    }
                }, throwable -> {
                    llStateful.showError(throwable.getMessage(), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            llStateful.showLoading();
                            getDetails();
                        }
                    });
                });
    }

}
