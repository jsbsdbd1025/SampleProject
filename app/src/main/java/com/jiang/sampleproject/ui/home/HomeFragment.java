package com.jiang.sampleproject.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.jiang.common.widget.CustomDialog;
import com.jiang.common.widget.flashview.BannerBean;
import com.jiang.common.widget.flashview.FlashView;
import com.jiang.common.widget.flashview.listener.FlashViewListener;
import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by jiang on 2017/2/28.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.fv_home)
    FlashView flashView;
    private List<BannerBean> bannerBeenList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.frag_home;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void init(View view) {

        for (int i = 0; i < 3; i++) {
            bannerBeenList.add(new BannerBean());
        }
        flashView.setDatas(bannerBeenList);
        flashView.setOnPageClickListener(new FlashViewListener() {
            @Override
            public void onClick(int position) {

            }
        });

//        EditText editText = (EditText) LayoutInflater.from(mContext).inflate(R.layout.layout_edit_box, null);

        final CustomDialog.Builder dialog = new CustomDialog.Builder(mContext, R.style.CustomDialog);
        dialog.setTitle("备注详情")
                .setMessage("Hello World!!")
//                .setView(editText)
                .setPositiveButton("本大王知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                })
//                .setNegativeButton("取消", null)
                .create();
        dialog.show();
    }

}
