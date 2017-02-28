package com.jiang.sampleproject.ui.home;

import android.view.View;

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
    }

}
