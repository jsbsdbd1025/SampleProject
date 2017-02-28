package com.jiang.sampleproject.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.jiang.common.R;
import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.AppManager;
import com.jiang.common.utils.StatusBarCompat;
import com.jiang.common.utils.TUtil;
import com.jiang.common.utils.ToastUtil;
import com.jiang.common.widget.LoadingDialog;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter, E> extends CommonActivity {

    public T mPresenter;
    public E mModel;
    public Context mContext;
    private int count = -1;

    protected String TAG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        init();
        initPresenter();

        TAG = getClass().getSimpleName();
    }


    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    protected abstract void init();

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //避免mvp的内存泄漏
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        // 默认着色状态栏
//        SetStatusBarColor();
//        SetTranslanteBar();
    }


    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    /**
     * activity动画的设置
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.aim_common_right_in,
                R.anim.aim_common_left_out);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.aim_common_right_in,
                R.anim.aim_common_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.aim_common_left_in,
                R.anim.aim_common_right_out);
    }

    /**
     * activity动画的设置
     */

    public void startActivityByLeft(Intent intent) {
        count = 1;
        startActivity(intent);
        overridePendingTransition(R.anim.aim_common_right_in,
                R.anim.aim_common_left_out);
    }

    public void startActivityByRight(Intent intent) {
        count = 2;
        startActivity(intent);
        overridePendingTransition(R.anim.aim_common_left_in,
                R.anim.aim_common_right_out);
    }

    public void startActivityByUp(Intent intent) {
        count = 3;
        startActivity(intent);
        overridePendingTransition(R.anim.aim_common_down_in,
                R.anim.aim_common_up_out);
    }


    public void startActivityForResultByUp(Intent intent, int requestCode) {
        count = 3;
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.aim_common_down_in,
                R.anim.aim_common_up_out);
    }

    public void startActivityForResultByLeft(Intent intent, int requestCode) {
        count = 1;
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.aim_common_right_in,
                R.anim.aim_common_left_out);
    }

    public void finishByDown() {
        finish();
        overridePendingTransition(R.anim.aim_common_up_in,
                R.anim.aim_common_down_out);
    }

    public void finishByRight() {
        finish();
        overridePendingTransition(R.anim.aim_common_left_in,
                R.anim.aim_common_right_out);
    }

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/
    protected void setComponet() {
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.main_color));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (count == 1) {
                finishByRight();
            } else if (count == 2) {
                finishByDown();
            } else if (count == 3) {
                finish();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoadingDialog(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUtil.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUtil.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUtil.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUtil.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUtil.showToastWithImg(text, res);
    }

    /**
     * 带图片的toast
     *
     * @param text 操作成功提示
     */
    public void showToastWithImgAndSuc(String text) {
        ToastUtil.showToastWithImgAndSuc(text);
    }

    public View getContentView() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        FrameLayout content = (FrameLayout) view.findViewById(android.R.id.content);
        return content;
//        return ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
    }


}
