package com.example.administrator.zhbj74;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj74.Utils.spUtils;

/**
 * 闪屏页面的动画
 */
public class SplashActivity extends Activity {

    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //初始化UI
        initUI();

        //执行动画的方法
        initAnimation();
    }


    //初始化界面的方法
    private void initUI() {
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
    }

    //执行动画的方法
    private void initAnimation() {
        //旋转动画
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(1000);//动画的执行时间
        rotateAnimation.setFillAfter(true);//设置动画保持结束状态

        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);

        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);

        //动画集合
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);

        //启动动画
        rlRoot.startAnimation(set);

        //监听动画结束后，跳转相应的页面
        set.setAnimationListener(new Animation.AnimationListener() {



            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始执行的时候
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束的时候
                /**
                 * 动画结束，跳转页面
                 * 第一次使用跳转到新手引导页
                 * 非第一次使用进入主页面
                 * 用sharePreference存储用户信息
                 * isFirstEnter是否第一次进入的标志
                 */
                Boolean firstEnter = spUtils.getBoolean(getApplicationContext(), "isFirstEnter", true);
                 Intent intent;
                if (firstEnter){
                    //进入引导页
                    intent = new Intent(getApplicationContext(), GuideActivity.class);
                    //将第一次的标志改为false
                    //spUtils.setBoolean(getApplicationContext(),"isFirstEnter",false);
                }else {
                    //进入主页面
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                }

                startActivity(intent);

                finish();//退出闪屏页面

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重放的时候
            }
        });

    }


}
