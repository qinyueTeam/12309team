/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qinyue.monitor.web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;

import com.qinyue.monitor.R;
import com.qinyue.monitor.constant.TagConstant;
import com.xuexiang.xui.widget.slideback.SlideBack;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;


/**
 * 壳浏览器
 *
 * @author xuexiang
 * @since 2019/1/5 上午12:15
 */
public class AgentWebActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);

        SlideBack.with(this)
                .haveScroll(true)
                .callBack(this::finish)
                .register();

//        Uri uri = getIntent().getData();
        openFragment(getIntent().getStringExtra(AgentWebFragment.KEY_URL));
//        if (uri != null) {
//            XRouter.getInstance().build(uri).navigation(this, new NavCallback() {
//                @Override
//                public void onArrival(Postcard postcard) {
//                    finish();
//                }
//            });
//        } else {
//            String url = getIntent().getStringExtra(KEY_URL);
//            if (url != null) {
//                openFragment(url);
//            } else {
//                XToastUtils.toast("数据出错！");
//                finish();
//            }
//        }
    }

    private AgentWebFragment mAgentWebFragment;

    private void openFragment(String url) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container_frame_layout, mAgentWebFragment = AgentWebFragment.getInstance(url));
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //一定要保证 mAentWebFragemnt 回调
//		mAgentWebFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        AgentWebFragment agentWebFragment = mAgentWebFragment;
        if (agentWebFragment != null) {
            if (((FragmentKeyDown) agentWebFragment).onFragmentKeyDown(keyCode, event)) {
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        SlideBack.unregister(this);
        super.onDestroy();
    }
}
