# day01
---
- 创建项目
- 欢迎页
- 引导页
- 主页面架构

## 1. 创建项目
**步骤**

~~~
1. 打开Android Studio
2. 修改要创建的项目名称和包名以及项目在硬盘上的位置
3. 选择支持的系统版本
4. 创建一个空的Activity
5. 修改创建Activity的名字
6. 创建完成
~~~

1. 打开Android Studio
	
2. 修改要创建的项目名称和包名以及项目在硬盘上的位置
3. 选择支持的系统版本
4. 创建一个空的Activity
5. 修改创建Activity的名字
6. 创建完成

**总结**

使用Android Studio创建项目非常简单。就如同我们去吃一个苹果，先拿一个苹果，用水果刀削皮，开始吃苹果。so easy！

## 2. 欢迎页
**需求**

1. 展示2秒钟跳转到主页面
2. 透明度从0~1 

**技术选型**

1. 透明动画AlphaAnimation
2. 动画监听AnimationListener
3. 界面跳转startActivity(Intent(this, MainActivit.class))

**代码实现**

欢迎页的代码分两部分来实现，布局和Java代码。

~~~
activity_splash.xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/fl_splash"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/splash"
    tools:context=".ui.activity.SplashActivity" />
~~~
~~~
SplashActivity.java
package cn.zhaoliang5156.blog.gank.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;

import cn.zhaoliang5156.blog.gank.R;
import cn.zhaoliang5156.blog.gank.util.CacheUtils;
import cn.zhaoliang5156.blog.gank.util.Config;
import cn.zhaoliang5156.blog.gank.util.Constants;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/21 9:02 AM
 * Description: App的欢迎页
 */
public class SplashActivity extends AppCompatActivity {

    // 声明控件
    private FrameLayout flSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen();
        setContentView(R.layout.activity_splash);

        bindView();
        anim();
    }

    /**
     * 全屏
     */
    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 创建并执行动画
     */
    private void anim() {
        // 创建Alpha动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0F, 1F);
        // 设置动画持续时长
        alphaAnimation.setDuration(2000);
        // 设置动画监听
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // 动画开始播放
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画播放结束
                toGuideOrMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // 动画重复播放
            }
        });
        // 播放动画
        flSplash.startAnimation(alphaAnimation);
    }

    /**
     * 进入引导页或进入首页
     */
    private void toGuideOrMain() {
        Intent intent;
        if (CacheUtils.getBoolean(this, Constants.START_MAIN)) {
            // 进入主页
            intent = new Intent(this, MainActivity.class);
        } else {
            // 进入引导页
            intent = new Intent(this, GuideActivity.class);
        }
        // 切换界面
        startActivity(intent);
        // 销毁界面
        finish();
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        flSplash = findViewById(R.id.fl_splash);
    }
}
~~~

**总结**

欢迎页的实现方式有很多种，需要根据需求选择不同的技术去实现。一般情况下都是延时跳转。

## 引导页
**需求**
**技术选型**
**代码实现**

灰色小圆点

~~~
point_gray.xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">

    <solid android:color="@android:color/darker_gray" />
</shape>
~~~

红色小圆点

~~~
point_red.xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">

    <solid android:color="@android:color/holo_red_light" />
</shape>
~~~

布局

~~~
activity_guide.xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".ui.activity.GuideActivity">

    <!-- 引导页图片 -->
    <android.support.v4.view.ViewPager
        android:id="@+id/guide_view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <!-- 开始体验按钮 -->
    <Button
        android:id="@+id/guide_btn_start_experience"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="80dp"
        android:background="@drawable/btn_start_experience"
        android:padding="5dp"
        android:text="开始体验"
        android:textColor="@android:color/white"
        android:textSize="18sp"
        android:visibility="gone" />

    <!-- 小圆点 -->
    <RelativeLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_centerHorizontal="true"
        android:layout_marginBottom="40dp">

        <!-- 灰色的点 代码动态添加 -->
        <LinearLayout
            android:id="@+id/guide_ll_points_group"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal" />

        <!-- 红色的点 -->
        <ImageView
            android:id="@+id/guide_iv_red_point"
            android:layout_width="10dp"
            android:layout_height="10dp"
            android:background="@drawable/point_red" />
    </RelativeLayout>
</RelativeLayout>
~~~

Activity

~~~
GuideActivity.java
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
~~~

**总结**

## 主页面架构
**需求**
**技术选型**
**代码实现**
**总结**





