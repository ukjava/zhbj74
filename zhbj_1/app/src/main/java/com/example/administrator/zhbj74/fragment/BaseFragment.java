package com.example.administrator.zhbj74.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**创建一个子fragment的父类
 * Created by Administrator on 2018/11/5.
 */

public abstract class BaseFragment extends Fragment {

    public FragmentActivity mActivity;

    //fragment的创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取当前fragment所依赖的Activity
        mActivity = getActivity();
    }


    //初始化fragment的布局,可以让子类自己去实现这个方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //初始化布局，必须由子类实现
        View view = initView();

        return view;

    }

    //当前fragment所依赖的Activity的oncreate方法执行结束
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        initData();

    }


    //子类必须实现的定义布局抽象方法
    public abstract View initView();

    //子类必须实现初始化数据抽象方法
    public abstract void initData();

}
