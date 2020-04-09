package com.qinyue.monitor.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SPUtils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.constant.TagConstant;
import com.qinyue.monitor.splash.GuideActivity;
import com.qinyue.monitor.splash.SplashActivity;
import com.xuexiang.xui.widget.toast.XToast;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    private int CheckedIndex = 0;
    private List<String> list = new ArrayList<>();
    private List<Integer> listImgYes = new ArrayList<>();
    private List<Integer> listImgNo = new ArrayList<>();
    List<Fragment> mFragments = new ArrayList<>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    CommonNavigator commonNavigator;

    @Override
    public String initTitleText() {
        return "首页";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initview() {
        list.add("首页");
        listImgYes.add(R.mipmap.icon_first_yes);
        listImgYes.add(R.mipmap.icon_work_yes);
        listImgYes.add(R.mipmap.icon_my_yes);
        listImgNo.add(R.mipmap.icon_first_no);
        listImgNo.add(R.mipmap.icon_work_no);
        listImgNo.add(R.mipmap.icon_my_no);
        list.add("办事");
        list.add("个人中心");
        mFragments.add(new FirstFragment());
        mFragments.add(new WorkFragment());
        mFragments.add(new MyFragment());
        initMagicIndicator1();
        mFragmentContainerHelper.handlePageSelected(CheckedIndex, false);
        switchPages(CheckedIndex);
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        setTitleIconLeftVisible(false);
    }

    @Override
    protected Boolean status() {
        return null;
    }

    private void initMagicIndicator1() {
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setBackgroundColor(Color.TRANSPARENT);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int i) {
                CommonPagerTitleView commonPagerTitleView = new CommonPagerTitleView(context);

                // load custom layout
                View customLayout = LayoutInflater.from(context).inflate(R.layout.simple_pager_title_layout2, null);
                final ImageView titleImg = customLayout.findViewById(R.id.img);
                final TextView titleText = customLayout.findViewById(R.id.tv);
                titleText.setText(list.get(i));
                commonPagerTitleView.setContentView(customLayout);

                commonPagerTitleView.setOnPagerTitleChangeListener(new CommonPagerTitleView.OnPagerTitleChangeListener() {

                    @Override
                    public void onSelected(int index, int totalCount) {
                        titleText.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.home_color));
                        titleImg.setImageResource(listImgYes.get(i));
//                        RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) titleImg.getLayoutParams();
//                        layoutParams.width= ConvertUtils.dp2px(35);
//                        layoutParams.height=ConvertUtils.dp2px(35);
//                        titleImg.setLayoutParams(layoutParams);

                    }

                    @Override
                    public void onDeselected(int index, int totalCount) {
                        titleText.setTextColor(Color.parseColor("#666666"));
                        titleImg.setImageResource(listImgNo.get(i));
//                        RelativeLayout.LayoutParams layoutParams =(RelativeLayout.LayoutParams) titleImg.getLayoutParams();
//                        layoutParams.width=ConvertUtils.dp2px(20);
//                        layoutParams.height=ConvertUtils.dp2px(20);
//                        titleImg.setLayoutParams(layoutParams);
                    }

                    @Override
                    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
//                        titleImg.setScaleX(0.7f * leavePercent);
//                        titleImg.setScaleY(0.7f * leavePercent);
                    }

                    @Override
                    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
//                        titleImg.setScaleX(1.2f * enterPercent);
//                        titleImg.setScaleY(1.2f * enterPercent);
                    }
                });

                commonPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedIndex = i;
                        mFragmentContainerHelper.handlePageSelected(i);
                        switchPages(i);
                    }
                });

                return commonPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
//        XXPermissions.with(this)
//                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                //.constantRequest()
//                // 支持请求6.0悬浮窗权限8.0请求安装权限
//                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
//                // 不指定权限则自动获取清单中的危险权限
//                .permission(Permission.RECORD_AUDIO)
//                .request(new OnPermission() {
//
//                    @Override
//                    public void hasPermission(List<String> granted, boolean all) {
//
//                    }
//
//                    @Override
//                    public void noPermission(List<String> denied, boolean quick) {
//                        XToast.error(MainActivity.this, "我们需要使用麦克风权限").show();
//                    }
//                });
        checkPermissions();
    }
    private void checkPermissions() {

        List<String> permissions = new LinkedList<>();

        addPermission(permissions, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(permissions, Manifest.permission.RECORD_AUDIO);
        addPermission(permissions, Manifest.permission.INTERNET);
        addPermission(permissions, Manifest.permission.READ_PHONE_STATE);

        if (!permissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]),
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
        }

    }
    private void addPermission(List<String> permissionList, String permission) {

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
        }
    }

    LinkedHashMap<String, String> resMap = new LinkedHashMap<>();
    private String buildMessage(Map<String, String> msg) {

        StringBuffer stringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> iter = msg.entrySet().iterator();
        while (iter.hasNext()) {
            String value = iter.next().getValue();
            stringBuffer.append(value+"\r\n");
        }
        return stringBuffer.toString();
    }
    int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private void switchPages(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment;
        for (int i = 0, j = mFragments.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mFragments.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            } else {
                fragmentTransaction.add(R.id.fl_fragment_content, fragment);
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mFragments.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fl_fragment_content, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        switch (list.get(index)) {
            case "首页":
                setTitleText("首页");
                break;
            case "办事":
                setTitleText("办事");
                break;
            case "个人中心":
                setTitleText("个人中心");
                break;
        }
    }


}
