package com.qinyue.monitor.my;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.qinyue.monitor.R;
import com.qinyue.monitor.base.BaseActivity;
import com.qinyue.monitor.view.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * 创建人:qinyue
 * 创建日期:2020/3/28
 * 描述:
 **/
public class MyXFActivity extends BaseActivity {
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    private String[] strings = {"普通信访", "未成年人司法保护", "公益诉讼"};
    CommonNavigator commonNavigator;
    private int checkIndex = 0;
    MyXFPagerAdapter myXFPagerAdapter;
    @Override
    public String initTitleText() {
        return "我的信访";
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onClickTitleLeft() {
        super.onClickTitleLeft();
        finish();
    }

    @Override
    protected void initview() {
        myXFPagerAdapter = new MyXFPagerAdapter(this,strings);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(myXFPagerAdapter);
        commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return strings.length;
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int i) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(strings[i]);
                simplePagerTitleView.setTextSize(14);
                simplePagerTitleView.setPadding(5, 5, 5, 5);
                simplePagerTitleView.setNormalColor(ContextCompat.getColor(MyXFActivity.this, R.color.text_color));
                simplePagerTitleView.setSelectedColor(ContextCompat.getColor(MyXFActivity.this, R.color.text_color));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(i);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);
                return badgePagerTitleView;
            }
            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setLineHeight(UIUtil.dip2px(context, 1));
                indicator.setColors(ContextCompat.getColor(MyXFActivity.this, R.color.home_color));
                return indicator;
            }

            @Override
            public float getTitleWeight(Context context, int index) {
                if (index == 0) {
                    return 1.0f;
                } else if (index == 1) {
                    return 1.4f;
                } else {
                    return 1.0f;
                }
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                checkIndex = position;
                myXFPagerAdapter.setSelectItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected boolean network() {
        return false;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activty_myxf;
    }

    @Override
    protected void init() {

    }

    @Override
    protected Boolean status() {
        return false;
    }
}
