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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

}
