package com.qinyue.monitor.first;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.base.BaseArrayDataBean2;
import com.qinyue.monitor.base.BaseDataBean;
import com.qinyue.monitor.base.BaseResBean;
import com.qinyue.monitor.constant.NetConstant;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.my.JczxxMsgBean;
import com.qinyue.monitor.util.Base64Converter;
import com.qinyue.monitor.util.JsonUtils;
import com.qinyue.monitor.util.UserUtils;
import com.qinyue.monitor.view.ChooseAfdActivity;
import com.qinyue.monitor.view.ChooseSfActivity;
import com.qinyue.monitor.view.ChooseSxxzActivity;
import com.qinyue.monitor.view.ChooseTsSfActivity;
import com.qinyue.monitor.view.ParentEntity;
import com.qinyue.monitor.view.SelectSectionParentEntity;
import com.xuexiang.xui.widget.button.shadowbutton.ShadowButton;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.bottomsheet.BottomSheet;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.OptionsPickerView;
import com.xuexiang.xui.widget.picker.widget.builder.OptionsPickerBuilder;
import com.xuexiang.xui.widget.picker.widget.listener.OnOptionsSelectListener;
import com.xuexiang.xui.widget.toast.XToast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rxhttp.wrapper.param.RxHttp;
import rxhttp.wrapper.parse.SimpleParser;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/31
 * 描述:
 **/
public class JwjdActivty extends BaseActivity {
    @BindView(R.id.lin_jbr)
    LinearLayout jbrLin;
    @BindView(R.id.content)
    MultiLineEditText content;
    @BindView(R.id.lin_bjbr)
    LinearLayout bjbrLin;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    SparseArray<View> jbrViews = new SparseArray<>();
    SparseArray<View> bjbrViews = new SparseArray<>();
    SparseArray<SelectSectionParentEntity> jbrAfdDatas = new SparseArray<>();
    SparseArray<SelectSectionParentEntity> sfDatas = new SparseArray<>();
    SparseArray<SelectSectionParentEntity> tssfDatas = new SparseArray<>();
    SparseArray<SelectSectionParentEntity> bjbrAfdDatas = new SparseArray<>();
    SparseArray<ParentEntity> bjbrZysxDatas = new SparseArray<>();
    SparseArray<ParentEntity> bjbrQtsxDatas = new SparseArray<>();
    private String[] mSmStr = {"是", "否"};
    private String[] mSexStr = {"未知", "男","女"};
    private int[] smSelectOption = {0, 0, 0};
    private int[] mzSelectOption = {-1, -1, -1};
    private int[] zzmmSelectOption = {-1, -1, -1};
    private int[] sxlySelectOption = {-1, -1, -1};
    private int[] checkIndexSex = {-1, -1, -1};
    List<MzBean> mzBeans;
    List<String> mzStrBeans = new ArrayList<>();
    List<ZzmmBean> zzmmBeans;
    List<String> zzmmStrBeans = new ArrayList<>();
    List<ZzmmBean> sxlyBeans;
    List<String> sxlyStrBeans = new ArrayList<>();

    private String title = "";

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
        zzmmBeans = JsonUtils.getInstance().gson.fromJson(getString(R.string.str_zzmm), new TypeToken<List<ZzmmBean>>() {
        }.getType());
        for (int i = 0; i < zzmmBeans.size(); i++) {
            zzmmStrBeans.add(zzmmBeans.get(i).getText());
        }
        sxlyBeans = JsonUtils.getInstance().gson.fromJson(getString(R.string.str_sxly), new TypeToken<List<ZzmmBean>>() {
        }.getType());
        for (int i = 0; i < sxlyBeans.size(); i++) {
            sxlyStrBeans.add(sxlyBeans.get(i).getText());
        }
    }

    @Override
    protected void initview() {
        jbrLin.addView(addJbrView(0));
        bjbrLin.addView(addBjbrView(0));
        title = getIntent().getStringExtra("title");
    }

    private View addJbrView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.view_jwjd_jbr, rootView, false);
        ShadowButton addButton = view.findViewById(R.id.but_add);
        ShadowButton delButton = view.findViewById(R.id.but_del);
        LinearLayout choose_sm = view.findViewById(R.id.choose_sm);
        LinearLayout choose_afd = view.findViewById(R.id.choose_afd);
        TextView sm = view.findViewById(R.id.sm);
        if (pos == 0) {
            addButton.setVisibility(View.VISIBLE);
            delButton.setVisibility(View.VISIBLE);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (jbrViews.size() < 3) {
                        jbrLin.addView(addJbrView(jbrViews.size()));
                    } else {
                        XToast.info(JwjdActivty.this, "最多只能添加3位举报人").show();
                    }
                }
            });
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deljbrView();
                }
            });
        } else {
            addButton.setVisibility(View.GONE);
            delButton.setVisibility(View.GONE);
        }
        choose_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSmPickerView(sm, pos);
            }
        });
        choose_afd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseAfdActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 100);
            }
        });
        jbrViews.put(jbrViews.size(), view);
        return view;
    }

    private View addBjbrView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.view_jwjd_bjbr, rootView, false);
        ShadowButton addButton = view.findViewById(R.id.but_add);
        ShadowButton delButton = view.findViewById(R.id.but_del);
        LinearLayout choose_sex = view.findViewById(R.id.choose_sex);
        LinearLayout choose_afd = view.findViewById(R.id.choose_afd);
        LinearLayout choose_mz = view.findViewById(R.id.choose_mz);
        LinearLayout choose_zzmm = view.findViewById(R.id.choose_zzmm);
        LinearLayout choose_sf = view.findViewById(R.id.choose_sf);
        LinearLayout choose_tssf = view.findViewById(R.id.choose_tssf);
        LinearLayout choose_sxly = view.findViewById(R.id.choose_sxly);
        LinearLayout choose_zysxxx = view.findViewById(R.id.choose_zysxxx);
        LinearLayout choose_qtsxxx = view.findViewById(R.id.choose_qtsxxx);
        TextView mz = view.findViewById(R.id.mz);
        TextView sex = view.findViewById(R.id.sex);
        TextView zzmm = view.findViewById(R.id.zzmm);
        TextView sxly = view.findViewById(R.id.sxly);
        if (pos == 0) {
            addButton.setVisibility(View.VISIBLE);
            delButton.setVisibility(View.VISIBLE);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bjbrViews.size() < 3) {
                        bjbrLin.addView(addBjbrView(bjbrViews.size()));
                    } else {
                        XToast.info(JwjdActivty.this, "最多只能添加3位被举报人").show();
                    }
                }
            });
            delButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delBjbrView();
                }
            });
        } else {
            addButton.setVisibility(View.GONE);
            delButton.setVisibility(View.GONE);
        }
        choose_sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSimpleBottomSheetList(pos, sex);
            }
        });
        choose_afd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseAfdActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 101);
            }
        });
        choose_sf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseSfActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 102);
            }
        });
        choose_tssf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseTsSfActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 103);
            }
        });
        choose_qtsxxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseSxxzActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 104);
            }
        });
        choose_zysxxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JwjdActivty.this, ChooseSxxzActivity.class);
                intent.putExtra("index", pos);
                startActivityForResult(intent, 105);
            }
        });
        choose_mz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mzBeans == null) {
                    getMzDataForcChild(pos, mz);
                } else {
                    showMzPickerView(mz, pos);
                }
            }
        });
        choose_zzmm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (zzmmBeans == null) {
                    XToast.error(JwjdActivty.this, "数据解析异常").show();
                } else {
                    showZzmmPickerView(zzmm, pos);
                }
            }
        });
        choose_sxly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sxlyBeans == null) {
                    XToast.error(JwjdActivty.this, "数据解析异常").show();
                } else {
                    showSxlyPickerView(sxly, pos);
                }
            }
        });
        bjbrViews.put(bjbrViews.size(), view);
        return view;
    }

    private void delBjbrView() {
        DialogLoader.getInstance().showConfirmDialog(
                JwjdActivty.this,
                "确定删除第" + bjbrViews.size() + "位被举报人?",
                "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (bjbrViews.size() == 2) {
                            bjbrLin.removeView(bjbrViews.get(1));
                            bjbrViews.remove(1);
                        }
                        if (bjbrViews.size() == 3) {
                            bjbrLin.removeView(bjbrViews.get(2));
                            bjbrViews.remove(2);
                        }
                        dialog.dismiss();
                    }
                },
                "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
    }

    private void deljbrView() {
        DialogLoader.getInstance().showConfirmDialog(
                JwjdActivty.this,
                "确定删除第" + jbrViews.size() + "位举报人?",
                "确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (jbrViews.size() == 2) {
                            jbrLin.removeView(jbrViews.get(1));
                            jbrViews.remove(1);
                        }
                        if (jbrViews.size() == 3) {
                            jbrLin.removeView(jbrViews.get(2));
                            jbrViews.remove(2);
                        }
                        dialog.dismiss();
                    }
                },
                "取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }
        );
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_jwjd;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }

    /**
     * 署名选择
     */
    private void showSmPickerView(TextView tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(mSmStr[options1]);
                smSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("是否署名")
                .setSelectOptions(smSelectOption[where])
                .build();
        pvOptions.setPicker(mSmStr);
        pvOptions.show();
    }

    /**
     * 民族
     */
    private void showMzPickerView(TextView tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(mzBeans.get(options1).getName());
                mzSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("民族")
                .setSelectOptions(mzSelectOption[where])
                .build();
        pvOptions.setPicker(mzStrBeans);
        pvOptions.show();
    }

    /**
     * 政治面貌
     */
    private void showZzmmPickerView(TextView tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(zzmmBeans.get(options1).getText());
                zzmmSelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("政治面貌")
                .setSelectOptions(zzmmSelectOption[where])
                .build();
        pvOptions.setPicker(zzmmStrBeans);
        pvOptions.show();
    }

    /**
     * 涉嫌领域
     */
    private void showSxlyPickerView(TextView tv, int where) {
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public boolean onOptionsSelect(View v, int options1, int options2, int options3) {
                tv.setText(sxlyBeans.get(options1).getText());
                sxlySelectOption[where] = options1;
                return false;
            }
        })
                .setTitleText("涉嫌领域")
                .setSelectOptions(sxlySelectOption[where])
                .build();
        pvOptions.setPicker(sxlyStrBeans);
        pvOptions.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 123: {//举报人和被举报人选择案发地返回
                SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
                int index = data.getIntExtra("index", 0);
                if (requestCode == 100) {
                    View view = jbrViews.get(index);
                    TextView dq = view.findViewById(R.id.dq);
                    dq.setText(sectionParentEntity.getName() + "");
                    jbrAfdDatas.put(index, sectionParentEntity);
                } else {
                    View view = bjbrViews.get(index);
                    TextView dq = view.findViewById(R.id.afd);
                    dq.setText(sectionParentEntity.getName() + "");
                    bjbrAfdDatas.put(index, sectionParentEntity);
                }

            }
            break;
            case 124: {//被举报人身份
                SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
                int index = data.getIntExtra("index", 0);
                View view = bjbrViews.get(index);
                TextView dq = view.findViewById(R.id.sf);
                dq.setText(sectionParentEntity.getName());
                sfDatas.put(index, sectionParentEntity);
            }
            break;
            case 125: {//被举报人特殊身份
                SelectSectionParentEntity sectionParentEntity = (SelectSectionParentEntity) data.getSerializableExtra("data");
                int index = data.getIntExtra("index", 0);
                View view = bjbrViews.get(index);
                TextView dq = view.findViewById(R.id.tssf);
                dq.setText(sectionParentEntity.getName());
                tssfDatas.put(index, sectionParentEntity);
            }
            break;
            case 126: {
                ParentEntity sectionParentEntity = (ParentEntity) data.getSerializableExtra("data");
                int index = data.getIntExtra("index", 0);
                View view = bjbrViews.get(index);
                if (requestCode == 105) {
                    //被举报人主要涉嫌性质
                    TextView dq = view.findViewById(R.id.zysxxx);
                    dq.setText(sectionParentEntity.getName());
                    bjbrZysxDatas.put(index, sectionParentEntity);
                } else {
                    //其它
                    TextView dq = view.findViewById(R.id.qtsxxx);
                    dq.setText(sectionParentEntity.getName());
                    bjbrQtsxDatas.put(index, sectionParentEntity);
                }


            }
            break;
        }
    }

    private void showSimpleBottomSheetList(int pos, TextView textView) {
        new BottomSheet.BottomListSheetBuilder(this)
                .setTitle("请选择性别")
                .addItem("未知")
                .addItem("男")
                .addItem("女")
                .setCheckedIndex(checkIndexSex[pos])
                .setIsCenter(true)
                .setOnSheetItemClickListener(new BottomSheet.BottomListSheetBuilder.OnSheetItemClickListener() {
                    @Override
                    public void onClick(BottomSheet dialog, View itemView, int position, String tag) {
                        dialog.dismiss();
                        checkIndexSex[pos] = position;
                        textView.setText(tag);
                    }
                })
                .build().show();
    }

    public void getMzDataForcChild(int pos, TextView textView) {
        miniLoadingDialog.show();
        Disposable subscribe = RxHttp.postForm(TagConstant.BASEURL + NetConstant.getTypeCode)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("type", "2")
                .asParser(new SimpleParser<BaseArrayDataBean2<MzBean>>() {
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    miniLoadingDialog.dismiss();
                    if (s.getResult() == 200) {
                        mzBeans = s.getData();
                        for (int i = 0; i < mzBeans.size(); i++) {
                            mzStrBeans.add(mzBeans.get(i).getName());
                        }
                        showMzPickerView(textView, pos);
                    } else {
                        XToast.error(JwjdActivty.this, s.getMsg()).show();
                    }
                }, throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(JwjdActivty.this, throwable.getMessage()).show();
                });
    }

    @OnClick({R.id.but_yes, R.id.but_no})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_yes: {//提交
                for (int i = 0; i < bjbrViews.size(); i++) {
                    View view1 = bjbrViews.get(i);
                    TextView name = view1.findViewById(R.id.et_name);
                    TextView et_dw = view1.findViewById(R.id.et_dw);
                    if (name.getText().toString().trim().isEmpty()) {
                        XToast.info(JwjdActivty.this, "请填写第" + (i + 1) + "位被举报人的姓名").show();
                        return;
                    }
                    if (et_dw.getText().toString().trim().isEmpty()) {
                        XToast.info(JwjdActivty.this, "请填写第" + (i + 1) + "位被举报人的单位").show();
                        return;
                    }
                    if (bjbrAfdDatas.get(i) == null) {
                        XToast.info(JwjdActivty.this, "请选择第" + (i + 1) + "位被举报人的案发地").show();
                        return;
                    }
                    if (sfDatas.get(i) == null) {
                        XToast.info(JwjdActivty.this, "请选择第" + (i + 1) + "位被举报人的身份").show();
                        return;
                    }
                    if (bjbrZysxDatas.get(i) == null) {
                        XToast.info(JwjdActivty.this, "请选择第" + (i + 1) + "位被举报人的主要涉嫌性质").show();
                        return;
                    }
                }
                submit();
            }
            break;
            case R.id.but_no: {//取消
                finish();
            }
            break;
        }
    }

    @SuppressLint("CheckResult")
    private void submit() {
        miniLoadingDialog.show();
        Map<String, String> stringMap = new HashMap<>();
        for (int i = 0; i < jbrViews.size(); i++) {
            View view = jbrViews.get(i);
            EditText et_name = view.findViewById(R.id.et_name);
            EditText et_idcard = view.findViewById(R.id.et_idcard);
            EditText et_phone = view.findViewById(R.id.et_phone);
            EditText et_address = view.findViewById(R.id.et_address);
            switch (i) {
                case 0: {
                    stringMap.put("usernme",et_name.getText().toString().trim());
                    stringMap.put("signature",smSelectOption[i]==0?"是":"否");
                    stringMap.put("id_card",et_idcard.getText().toString().trim());
                    stringMap.put("mobile",et_phone.getText().toString().trim());
                    stringMap.put("area",jbrAfdDatas.get(i)==null?"":jbrAfdDatas.get(i).getName());
                    stringMap.put("address",et_address.getText().toString().trim());
                }
                break;
                case 1: {
                    stringMap.put("usernme1",et_name.getText().toString().trim());
                    stringMap.put("signature1",smSelectOption[i]==0?"是":"否");
                    stringMap.put("id_card1",et_idcard.getText().toString().trim());
                    stringMap.put("mobile1",et_phone.getText().toString().trim());
                    stringMap.put("area1",jbrAfdDatas.get(i)==null?"":jbrAfdDatas.get(i).getName());
                    stringMap.put("address1",et_address.getText().toString().trim());
                }
                break;
                case 2: {
                    stringMap.put("usernme2",et_name.getText().toString().trim());
                    stringMap.put("signature2",smSelectOption[i]==0?"是":"否");
                    stringMap.put("id_card2",et_idcard.getText().toString().trim());
                    stringMap.put("mobile2",et_phone.getText().toString().trim());
                    stringMap.put("area2",jbrAfdDatas.get(i)==null?"":jbrAfdDatas.get(i).getName());
                    stringMap.put("address2",et_address.getText().toString().trim());
                }
                break;
            }
        }
        for (int i = 0; i < bjbrViews.size(); i++) {
            View view = bjbrViews.get(i);
            EditText et_name = view.findViewById(R.id.et_name);
            EditText et_dw = view.findViewById(R.id.et_dw);
            EditText et_address = view.findViewById(R.id.et_address);
            EditText et_zw = view.findViewById(R.id.et_zw);
            EditText et_zj = view.findViewById(R.id.et_zj);
            EditText et_sxje = view.findViewById(R.id.et_sxje);
            switch (i) {
                case 0: {
                    stringMap.put("busername",et_name.getText().toString().trim());
                    stringMap.put("bsex",checkIndexSex[i]==-1?"":mSexStr[i]+"");
                    stringMap.put("bnational",mzSelectOption[i]==-1?"":mzBeans.get(mzSelectOption[i]).getCode());
                    stringMap.put("bzzmm",zzmmSelectOption[i]==-1?"":zzmmBeans.get(zzmmSelectOption[i]).getId());
                    stringMap.put("bunit",et_dw.getText().toString().trim());
                    stringMap.put("baddress",et_address.getText().toString().trim());
                    stringMap.put("bafdq",bjbrAfdDatas.get(i)==null?"":bjbrAfdDatas.get(i).getCode());
                    stringMap.put("bzhiji",et_zj.getText().toString().trim());
                    stringMap.put("bzhiwu",et_zw.getText().toString().trim());
                    stringMap.put("bshenfent",sfDatas.get(i)==null?"":sfDatas.get(i).getCode());
                    stringMap.put("btshenfen",tssfDatas.get(i)==null?"":tssfDatas.get(i).getCode());
                    stringMap.put("bsxly",sxlySelectOption[i]==-1?"":sxlyBeans.get(sxlySelectOption[i]).getId());
                    stringMap.put("bzysxxz",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getCode());
                    stringMap.put("bqtsxxz",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getCode());
                    stringMap.put("sxje",et_sxje.getText().toString().trim());
                }
                break;
                case 1: {
                    stringMap.put("busername1",et_name.getText().toString().trim());
                    stringMap.put("bsex1",checkIndexSex[i]==-1?"":mSexStr[i]+"");
                    stringMap.put("bnational1",mzSelectOption[i]==-1?"":mzBeans.get(mzSelectOption[i]).getName());
                    stringMap.put("bzzmm1",zzmmSelectOption[i]==-1?"":zzmmBeans.get(zzmmSelectOption[i]).getText());
                    stringMap.put("bunit1",et_dw.getText().toString().trim());
                    stringMap.put("baddress1",et_address.getText().toString().trim());
                    stringMap.put("bafdq1",bjbrAfdDatas.get(i)==null?"":bjbrAfdDatas.get(i).getName());
                    stringMap.put("bzhiji1",et_zj.getText().toString().trim());
                    stringMap.put("bzhiwu1",et_zw.getText().toString().trim());
                    stringMap.put("bshenfent1",sfDatas.get(i)==null?"":sfDatas.get(i).getName());
                    stringMap.put("btshenfen1",tssfDatas.get(i)==null?"":tssfDatas.get(i).getName());
                    stringMap.put("bsxly1",sxlySelectOption[i]==-1?"":sxlyBeans.get(sxlySelectOption[i]).getText());
                    stringMap.put("bzysxxz1",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getName());
                    stringMap.put("bqtsxxz1",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getName());
                    stringMap.put("sxje1",et_sxje.getText().toString().trim());
                }
                break;
                case 2: {
                    stringMap.put("busername2",et_name.getText().toString().trim());
                    stringMap.put("bsex2",checkIndexSex[i]==-1?"":mSexStr[i]+"");
                    stringMap.put("bnational2",mzSelectOption[i]==-1?"":mzBeans.get(mzSelectOption[i]).getName());
                    stringMap.put("bzzmm2",zzmmSelectOption[i]==-1?"":zzmmBeans.get(zzmmSelectOption[i]).getText());
                    stringMap.put("bunit2",et_dw.getText().toString().trim());
                    stringMap.put("baddress2",et_address.getText().toString().trim());
                    stringMap.put("bafdq2",bjbrAfdDatas.get(i)==null?"":bjbrAfdDatas.get(i).getName());
                    stringMap.put("bzhiji2",et_zj.getText().toString().trim());
                    stringMap.put("bzhiwu2",et_zw.getText().toString().trim());
                    stringMap.put("bshenfent2",sfDatas.get(i)==null?"":sfDatas.get(i).getName());
                    stringMap.put("btshenfen2",tssfDatas.get(i)==null?"":tssfDatas.get(i).getName());
                    stringMap.put("bsxly2",sxlySelectOption[i]==-1?"":sxlyBeans.get(sxlySelectOption[i]).getText());
                    stringMap.put("bzysxxz2",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getName());
                    stringMap.put("bqtsxxz2",bjbrZysxDatas.get(i)==null?"":bjbrZysxDatas.get(i).getName());
                    stringMap.put("sxje2",et_sxje.getText().toString().trim());
                }
                break;
            }
        }
        if (bjbrViews.size()==3){
            stringMap.put("bjubao2","false");
            stringMap.put("bjubao3","false");
        }
        if (bjbrViews.size()==2){
            stringMap.put("bjubao2","false");
            stringMap.put("bjubao3","true");
        }
        if (bjbrViews.size()==1){
            stringMap.put("bjubao2","true");
            stringMap.put("bjubao3","true");
        }
        stringMap.put("code",TagConstant.CODE);
        stringMap.put("content",content.getContentText());
        String s2 = new Gson().toJson(stringMap);
        String aesEncode = Base64Converter.AESEncode(TagConstant.AESKEY,s2) ;
        RxHttp.postForm(TagConstant.BASEURL+NetConstant.savejb)
                .add("appId", TagConstant.APPID)
                .add("code", TagConstant.CODE)
                .add("data", aesEncode)
                .asObject(BaseDataBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s->{
                    miniLoadingDialog.dismiss();
                    if (s.isResult()) {
                        XToast.success(JwjdActivty.this,s.getMsg()).show();
                        finish();
                    }else {
                        XToast.error(JwjdActivty.this,s.getMsg()).show();
                    }
                },throwable -> {
                    miniLoadingDialog.dismiss();
                    XToast.error(JwjdActivty.this,throwable.getMessage()).show();
                });
    }
}
