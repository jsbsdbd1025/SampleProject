package com.jiang.sampleproject.ui.me;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseFragment;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by jiang on 2017/2/28.
 */

public class MeFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindString(R.string.title_home)
    String strTitle;
    @Override
    public int getLayoutId() {
        return R.layout.frag_me;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void init(View view) {

        setHasOptionsMenu(true);
        tvTitle.setText(strTitle);
        toolbar.setNavigationIcon(R.mipmap.sel_home_nor);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        toolbar.setTitle("");
        toolbar.inflateMenu(R.menu.menu_home_right_button);
        toolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_1:
                showShortToast("Me第一个图标");
                break;
            case R.id.menu_2:
                showShortToast("Me第二个图标");
                break;
        }
        return false;
    }
}
