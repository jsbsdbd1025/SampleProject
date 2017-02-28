package com.jiang.common.base;

import android.content.Context;

import com.google.gson.Gson;

/**
 * 作者：yangxiaobo on 2016/11/29 18:46 邮箱：15869034922@163.com 描述:presenter的基类,联网工作的管理和数据库管理,通用业务的处理
 */
public abstract class BasePresenter<E extends BaseModel, T extends BaseView> {

    public Context mContext;
    protected Gson gson = new Gson();
    public E mModel;
    public T mView;

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public void onStart() {
    }


    public void onDestroy() {
        mView = null;
        if (mModel != null) {
            mModel = null;
        }
    }
}
