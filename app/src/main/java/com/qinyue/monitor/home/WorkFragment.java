package com.qinyue.monitor.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.qinyue.monitor.R;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.login.RegisterActivity;
import com.qinyue.monitor.work.AccusationActivity;
import com.qinyue.monitor.work.CriminalComPlaintActivity;
import com.qinyue.monitor.work.StateCompensationActivity;
import com.xuexiang.xui.widget.toast.XToast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/25
 * 描述:
 **/
public class WorkFragment extends Fragment{
    Unbinder unbinder;
    MainActivity mainActivity;
    @BindView(R.id.headimg)
    ImageView headImg;
    @BindView(R.id.ll_kg)
    LinearLayout ll_kg;
    @BindView(R.id.ll_xsss)
    LinearLayout ll_xsss;
    @BindView(R.id.ll_gjpc)
    LinearLayout ll_gjpc;
    @BindView(R.id.ll_msss)
    LinearLayout ll_msss;
    @BindView(R.id.xzss)
    LinearLayout xzss;
    @BindView(R.id.ll_qt)
    LinearLayout ll_qt;
    @BindView(R.id.img_wcn)
    ImageView img_wcn;
    @BindView(R.id.img_gyss)
    ImageView img_gyss;
    @BindView(R.id.ll_yysp)
    LinearLayout ll_yysp;
    @BindView(R.id.ll_yyck)
    LinearLayout ll_yyck;
    @BindView(R.id.yyls)
    LinearLayout yyls;
    @BindView(R.id.img_qzyj)
    ImageView img_qzyj;
    @BindView(R.id.img_flzx)
    ImageView img_flzx;
    private View view;

    public WorkFragment(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainActivity = (MainActivity) getActivity();
        Glide.with(this).load(TagConstant.BASEBANNER_URL).into(headImg);
        initOnCliclek();
        return view;
    }
@OnClick({R.id.ll_kg,R.id.ll_xsss,R.id.ll_gjpc})
public void onClick(View view){
    this.view = view;
    switch (view.getId()){
        case R.id.ll_kg:{
            Intent intent = new Intent(getActivity(), AccusationActivity.class);//要替换须知界面
            startActivity(intent);
        }break;
        case R.id.ll_xsss:{
            Intent intent = new Intent(getActivity(), CriminalComPlaintActivity.class);//要替换须知界面
            startActivity(intent);
        }break;
        case R.id.ll_gjpc:{
            Intent intent = new Intent(getActivity(), StateCompensationActivity.class);//要替换须知界面
            startActivity(intent);
        }break;
    }
}
    private void initOnCliclek() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
