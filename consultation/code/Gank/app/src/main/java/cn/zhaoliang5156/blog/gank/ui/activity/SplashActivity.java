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
