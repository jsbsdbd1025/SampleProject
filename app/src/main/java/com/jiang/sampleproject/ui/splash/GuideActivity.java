package com.jiang.sampleproject.ui.splash;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jiang.common.base.CommonActivity;
import com.jiang.common.utils.AppManager;
import com.jiang.common.widget.GuideView;
import com.jiang.sampleproject.R;
import com.jiang.sampleproject.base.BaseActivity;
import com.jiang.sampleproject.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jiang on 2017/2/28.
 */

public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.vp_guide)
    protected ViewPager viewPager;  //对应的viewPager
    private List<GuideView> viewList = new ArrayList<>();
    /**
     * 图片ID的数组
     */
    private int[] mImageIDs = new int[]{R.drawable.ph_error, R.drawable.ph_error, R.drawable.ph_error};

    @BindView(R.id.ly_dot_group)
    LinearLayout dotGroup;

    /**
     * 统一跳转入口
     *
     * @param activity
     */
    public static void startAction(CommonActivity activity) {
        Intent intent = new Intent(activity, GuideActivity.class);
        activity.startActivity(intent);
        AppManager.getAppManager().finishActivity();
    }

    @Override
    public int getLayoutId() {
        return R.layout.act_guide;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void init() {
        //初始化guide视图列表
        for (int i = 0; i < mImageIDs.length; i++) {
            GuideView guideView = new GuideView(this);
            guideView.setImageResource(mImageIDs[i]);
            viewList.add(guideView);
        }

        viewPager.setAdapter(pagerAdapter);

        addDotView();

        //设置可视页的转换监听 控制点的状态变换
        viewPager.addOnPageChangeListener(this);

    }

    private void addDotView() {
        dotGroup.removeAllViews();
        for (int i = 0; i < viewList.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.sl_guide_dot);
            view.setSelected(i == viewPager.getCurrentItem() ? true : false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getResources().getDimensionPixelSize(R.dimen.dot_size),
                    getResources().getDimensionPixelSize(R.dimen.dot_size));
            layoutParams.setMargins(15, 0, 15, 0);
            dotGroup.addView(view, layoutParams);
        }

    }

    @OnClick(R.id.btn_start)
    void displayHone() {
        MainActivity.startAction(this);
    }

    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    };

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        for (int i = 0; i < dotGroup.getChildCount(); i++) {
            dotGroup.getChildAt(i).setSelected(i == viewPager.getCurrentItem() ? true : false);
        }
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
