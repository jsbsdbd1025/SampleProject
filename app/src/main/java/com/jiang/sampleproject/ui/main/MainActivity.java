package com.jiang.sampleproject.ui.main;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.AppManager;
import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseActivity;
import com.jiang.sampleproject.base.BaseFragment;
import com.jiang.sampleproject.ui.home.HomeFragment;
import com.jiang.sampleproject.ui.me.MeFragment;
import com.jiang.sampleproject.ui.message.MessageFragment;
import com.jiang.sampleproject.ui.splash.GuideActivity;

import butterknife.BindView;

/**
 * Created by lenovo on 2017/2/28.
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.rg_main_container)
    RadioGroup mBottomContainer;
    private BaseFragment[] mFragments;
    private HomeFragment homeFragment;
    private MessageFragment msgFragment;
    private MeFragment meFragment;

    private boolean isExit = false;
    private Handler mHandler = new Handler();

    private int currentPosition = 0;

    public static void startAction(CommonActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        AppManager.getAppManager().finishActivity();
    }


    @Override
    public int getLayoutId() {
        return R.layout.act_main;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void init() {
        mFragments = new BaseFragment[3];
        homeFragment = (HomeFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.frag_main_home));
        msgFragment = (MessageFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.frag_main_msg));
        meFragment = (MeFragment) (getSupportFragmentManager()
                .findFragmentById(R.id.frag_main_me));

        mFragments[0] = homeFragment;
        mFragments[1] = msgFragment;
        mFragments[2] = meFragment;
        changeFragment(currentPosition);
        mBottomContainer.setOnCheckedChangeListener(this);
    }


    private void changeFragment(int index) {
        // 通过这个底部容器Item的index能够获取到对应的Fragment，需要将所有的Fragment对号放好（使用集合）
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.length; i++) {
            if (i == index) {
                ft.show(mFragments[i]);
            } else {
                ft.hide(mFragments[i]);
            }
        }
        ft.commit();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i) instanceof RadioButton) {
                RadioButton button = (RadioButton) group.getChildAt(i);
                if (button.getId() == checkedId) {
                    currentPosition = i;
                    changeFragment(i);
                }
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {// 返回键

            if (isExit) {
                finishRight();
            } else {
                isExit = true;
                showLongToast("再按一次退出应用");
                mHandler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 1000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
