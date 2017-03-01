package com.jiang.sampleproject.ui.message;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jiang.common.irecyclerview.IRecyclerView;
import com.jiang.common.widget.CustomDialog;
import com.jiang.common.widget.ToolBarBuilder;
import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseFragment;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by jiang on 2017/2/28.
 */

public class MessageFragment extends BaseFragment implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView tvTitle;
    @BindString(R.string.title_home)
    String strTitle;

    @BindView(R.id.recyclerview)
    IRecyclerView mRecyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.frag_msg;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void init(View view) {

        setHasOptionsMenu(true);
        new ToolBarBuilder(view)
                .setTitle(strTitle)
                .setNavigationIcon(R.mipmap.sel_home_nor)
                .setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                })
                .inflateMenu(R.menu.menu_home_right_button)
                .setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_1:
                showShortToast("Message第一个图标");
                break;
            case R.id.menu_2:
                showShortToast("Message第二个图标");
                break;
        }
        return false;
    }
}
