package com.jiang.sampleproject.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiang.common.base.BasePresenter;
import com.jiang.common.base.CommonFragment;
import com.jiang.common.utils.TUtil;

import butterknife.ButterKnife;

/**
 * Created by jiang on 2017/2/28.
 */

public abstract class BaseFragment<T extends BasePresenter, E> extends CommonFragment {
    public T mPresenter;
    public E mModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null) {
            mPresenter.mContext = this.getActivity();
        }
        init(rootView);
        initPresenter();
        return rootView;
    }

    /*********************
     * 子类实现
     *****************************/
    //获取布局文件
    public abstract int getLayoutId();

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    //初始化view
    protected abstract void init(View view);
}
