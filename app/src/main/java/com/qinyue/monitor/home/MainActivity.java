package com.qinyue.monitor.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ainirobot.coreservice.client.ApiListener;
import com.ainirobot.coreservice.client.RobotApi;
import com.ainirobot.coreservice.client.speech.SkillApi;
import com.ainirobot.coreservice.client.speech.SkillCallback;
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
import com.qinyue.monitor.yuying.ModuleCallback;
import com.xuexiang.xui.widget.toast.XToast;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView;

import java.util.ArrayList;
import java.util.List;

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
        XXPermissions.with(this)
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.constantRequest()
                // 支持请求6.0悬浮窗权限8.0请求安装权限
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                // 不指定权限则自动获取清单中的危险权限
                .permission(Permission.RECORD_AUDIO)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        lineServer();
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        XToast.error(MainActivity.this, "我们需要使用麦克风权限").show();
                    }
                });

    }

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

    final SkillApi skillApi = new SkillApi();

    private void line() {
        skillApi.connectApi(this, new ApiListener() {
            @Override
            public void handleApiDisabled() {
                Log.i("hhhhh", "失败");
            }

            @Override
            public void handleApiConnected() { //语⾳服务连接成功，注册语⾳回调
                skillApi.registerCallBack(mSkillCallback);
                Log.i("hhhhh", "语⾳服务连接成功");
            }

            @Override
            public void handleApiDisconnected() { //语⾳服务已断开
                Log.i("hhhhh", "语⾳服务已断开");
            }
        });
        skillApi.setRecognizable(true);
    }

    SkillCallback mSkillCallback = new SkillCallback() {
        @Override
        public void onSpeechParResult(String s) throws RemoteException { //语⾳临时识别结果
            Log.i("hhhhh", "语⾳临时识别结果:" + s);
        }

        @Override
        public void onStart() throws RemoteException { //开始识别
            Log.i("hhhhh", "语⾳开始识别");
        }

        @Override
        public void onStop() throws RemoteException { //识别结束
            Log.i("hhhhh", "语⾳识别结束");
        }

        @Override
        public void onVolumeChange(int volume) throws RemoteException { //识别的声⾳⼤⼩变化
        }

        /*** @param status 0 : 正常返回 * 1 : other返回 * 2 : 噪⾳或single_other返回 * 3 : 超时 * 4 : 被强制取消 * 5 : asr结果提前结束，未经过NLU * 6 : 全双⼯语意相同情况下，other返回 */
        @Override
        public void onQueryEnded(int status) throws RemoteException {
        }

        @Override
        public void onQueryAsrResult(String asrResult) throws RemoteException { //asrResult ：最终识别结果
            Log.i("hhhhh", "语⾳最终识别结果:" + asrResult);
        }
    };

    private void lineServer() {
        RobotApi.getInstance().connectServer(this, new ApiListener() {
            @Override
            public void handleApiDisabled() {
                Log.i("hhhhh", "连接服务器失败");
            }

            @Override
            public void handleApiConnected() { //Server已连接，设置接收请求的回调，包含语⾳指令、系统事件等
                RobotApi.getInstance().setCallback(new ModuleCallback());
                Log.i("hhhhh", "Server已连接");
                line();
            }

            @Override
            public void handleApiDisconnected() { //连接已断开
                Log.i("hhhhh", "连接已断开");
            }
        });
    }
}
