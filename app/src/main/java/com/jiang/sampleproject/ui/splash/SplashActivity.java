package com.jiang.sampleproject.ui.splash;

import android.os.Handler;

import com.jiang.common.utils.AppManager;
import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseActivity;

/**
 * 在appTheme中将 windowIsTranslucent 参数设置为true
 * 防止初始化过久导致的白屏问题
 * Created by jiang on 2017/2/28.
 */

public class SplashActivity extends BaseActivity {

    //设置闪屏页切换时间，利用handler 延迟操作
    private Handler mHandler = new Handler();
    private static final int DELAY_TIME = 3000;//ms

    @Override
    public int getLayoutId() {
        return R.layout.act_splash;
    }

    @Override
    public void initPresenter() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //延迟结束后进行的操作
                doNext();
            }
        }, DELAY_TIME);
    }

    /**
     * 通常进行是否首次进入应用判断
     * 跳转主页或者引导页
     */

    private void doNext() {
        GuideActivity.startAction(this);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    protected void init() {

    }
}
