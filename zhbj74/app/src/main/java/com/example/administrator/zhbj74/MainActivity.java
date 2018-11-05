package com.example.administrator.zhbj74;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.administrator.zhbj74.fragment.ContentFragment;
import com.example.administrator.zhbj74.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    private static final String TAG_LEFT_MENU = "TAG_LEFT_MENU";
    private static final String TAG_CONTENT= "TAG_CONTENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //除去屏幕标题
        setContentView(R.layout.activity_main);

        setBehindContentView(R.layout.left_menu);//设置一个侧边栏

        //设置全屏触摸
        SlidingMenu slidingMenu = getSlidingMenu();
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        slidingMenu.setBehindOffset(700);//给屏幕预留200像素

        initFragment();
    }

    //初始化fragment方法
    private  void initFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();//拿到fragment的管理器
        FragmentTransaction transaction = fragmentManager.beginTransaction();//开启事务

        transaction.replace(R.id.fl_left_fragment,//参数1是帧布局容器的id   参数2是要替换的fragment  参数3tag是标记
                new LeftMenuFragment(),TAG_LEFT_MENU);//用自己写的LeftMenufragment替换left_menu的fl_left_fragment帧布局

        transaction.replace(R.id.fl_main_fragment,
                new ContentFragment(),TAG_CONTENT);//用自己写的ContentFragment替换activity_main的fl_main_fragment帧布局

        transaction.commit();//提交事务
    }
}
