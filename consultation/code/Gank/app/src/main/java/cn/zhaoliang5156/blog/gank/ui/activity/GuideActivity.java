package cn.zhaoliang5156.blog.gank.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.zhaoliang5156.blog.gank.R;
import cn.zhaoliang5156.blog.gank.util.CacheUtils;
import cn.zhaoliang5156.blog.gank.util.Constants;
import cn.zhaoliang5156.blog.gank.util.DensityUtil;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/21 11:04 AM
 * Description: App的引导页
 */
public class GuideActivity extends AppCompatActivity {

    // 输出日志的tag
    private final String TAG = GuideActivity.class.getSimpleName();

    // 声明属性
    private ViewPager guideViewPager;
    private Button guideBtnStartExperience;
    private LinearLayout guideLlPointsGroup;
    private ImageView guideIvRedPoint;

    // viewPager图片集合
    private List<ImageView> imageViews;

    // 小圆的的间距
    private int pointSpacing;

    // 小圆点的大小
    private int widthPx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        // 绑定控件
        bindView();
        // 初始化数据
        initData();
        // 设置适配器
        guideViewPager.setAdapter(new GuidePagerAdpter());
        // 布局完成后获取间距
        guideIvRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new MyGlobalLayoutListener());
        // 滑动小红点
        guideViewPager.addOnPageChangeListener(new MyPageChangeListener());
        // 点击按钮进入主页
        guideBtnStartExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 保存曾经进入过主页面
                CacheUtils.pubBoolean(GuideActivity.this, Constants.START_MAIN, true);
                // 跳转页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                // 销毁页面
                finish();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // 初始化数据
        int[] ids = {
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        // 把小圆点的宽度转换成px，在界面上设置是10dp，用代码设置需要转换成px
        widthPx = DensityUtil.dip2px(this, 10);
        Log.i(TAG, "widthPx:" + widthPx);

        // 实例化ImageView集合
        imageViews = new ArrayList<>();
        // 遍历图片，创建ImageView
        for (int i = 0; i < ids.length; i++) {
            // 实例化ImageView对象
            ImageView imageView = new ImageView(this);
            // 设置背景
            imageView.setBackgroundResource(ids[i]);
            // 添加到集合
            imageViews.add(imageView);

            // 创建小圆点
            ImageView point = new ImageView(this);
            // 设置小圆点的背景
            point.setBackgroundResource(R.drawable.point_gray);
            // 创建布局参数对象，指定宽高 为10dp
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPx, widthPx);
            if (i != 0) {
                // 不是第一个小圆点，需要指定左边的距离，这样小圆点才能隔开，不然就连到一起了
                layoutParams.leftMargin = widthPx;
            }
            // 给小圆点设置布局参数
            point.setLayoutParams(layoutParams);
            // 布局中添加小圆点
            guideLlPointsGroup.addView(point);
        }
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        guideViewPager = findViewById(R.id.guide_view_pager);
        guideBtnStartExperience = findViewById(R.id.guide_btn_start_experience);
        guideLlPointsGroup = findViewById(R.id.guide_ll_points_group);
        guideIvRedPoint = findViewById(R.id.guide_iv_red_point);
    }

    /**
     * 页面滑动监听
     */
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 页面滑动的时候调用
         *
         * @param position             滑动那个页面
         * @param positionOffset       滑动的百分比
         * @param positionOffsetPixels 滑动的像素
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            // 计算小红点滑动的距离
            int leftMargin = (int) (position * pointSpacing + pointSpacing * positionOffset);
            // 获取布局参数
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) guideIvRedPoint.getLayoutParams();
            // 设置左边边距
            params.leftMargin = leftMargin;
            // 给小红点设置布局参数
            guideIvRedPoint.setLayoutParams(params);
        }

        /**
         * 页面选中的时候调用
         *
         * @param position 被选中的页面对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                // 最后一个页面
                guideBtnStartExperience.setVisibility(View.VISIBLE);
            } else {
                // 其他页面
                guideBtnStartExperience.setVisibility(View.INVISIBLE);
            }
        }

        /**
         * 页面滑动状态改变的时候调用
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 布局监听
     */
    class MyGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            // 移除监听
            guideIvRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(MyGlobalLayoutListener.this);
            // 读取小圆点的间距
            pointSpacing = guideLlPointsGroup.getChildAt(1).getLeft() - guideLlPointsGroup.getChildAt(0).getLeft();
            // 日志打印
            Log.i(TAG, "pointSpacing:" + pointSpacing);
        }
    }

    /**
     * 引导页适配器
     */
    class GuidePagerAdpter extends PagerAdapter {

        /**
         * 返回数据总个数
         *
         * @return
         */
        @Override
        public int getCount() {
            return imageViews.size();
        }

        /**
         * 判断
         *
         * @param view   当前创建的对象
         * @param object instantiateItem返回的对象
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        /**
         * 给指定的位置创建页面
         *
         * @param container ViewPager
         * @param position  要实例化的页面位置
         * @return 返回表示新页的对象。
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 销毁界面
         *
         * @param container ViewPager
         * @param position  要销毁的界面位置
         * @param object    要销毁的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
