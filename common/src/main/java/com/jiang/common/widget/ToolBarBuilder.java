package com.jiang.common.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jiang.common.R;

/**
 * Created by jiang on 2017/2/28.
 */

public class ToolBarBuilder {
    private View rootView;
    private Toolbar toolbar;
    private TextView tvTitle;
    private Context mContext;

    private final static int TITLE_TXT_SEZE = 16;

    public ToolBarBuilder(Activity context) {
        this.mContext = context;
        rootView = context.findViewById(R.id.toolbar);
        init();
    }

    public ToolBarBuilder(View view) {
        this.mContext = view.getContext();
        rootView = view.findViewById(R.id.toolbar);
        init();
    }

    private void init() {
        toolbar = (Toolbar) rootView;
        toolbar.setTitle("");
        tvTitle = (TextView) rootView.findViewById(R.id.toolbar_title);
    }

    public ToolBarBuilder setTitle(CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }
        return this;
    }

    public ToolBarBuilder inflateMenu(@MenuRes int resId) {
        toolbar.inflateMenu(resId);
        return this;
    }

    public ToolBarBuilder setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        toolbar.setOnMenuItemClickListener(listener);
        return this;
    }


    public ToolBarBuilder setNavigationIcon(@DrawableRes int resId) {
        setNavigationIcon(AppCompatResources.getDrawable(mContext, resId));
        return this;
    }

    public ToolBarBuilder setNavigationIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
        return this;
    }

    public ToolBarBuilder setNavigationOnClickListener(View.OnClickListener listener) {
        toolbar.setOnClickListener(listener);
        return this;
    }

}
