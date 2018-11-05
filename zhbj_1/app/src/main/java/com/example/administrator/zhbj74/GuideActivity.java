package com.example.administrator.zhbj74;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj74.Utils.spUtils;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager mViewPager;
    private int[] mImageIds;
    private ArrayList<ImageView> viewArrayList;
    private LinearLayout pointContainer;
    private ImageView redPoint;
    private int mPointDis;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //除去屏幕标题
        setContentView(R.layout.activity_guide);

        //初始化界面
        initUI();

        //初始化数据
        initData();

        //设置适配器
        initAdapter();

        //viewPager设置监听
        initRedPoint();


        // 计算两个圆点的距离
        // 移动距离=第二个圆点left值 - 第一个圆点left值
        // measure->layout(确定位置)->draw(activity的onCreate方法执行结束之后才会走此流程)
        // mPointDis = llContainer.getChildAt(1).getLeft()
        // - llContainer.getChildAt(0).getLeft();
        // System.out.println("圆点距离:" + mPointDis);

        // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
        // 视图树
        layoutListener();

        initBtn();
    }

    //按钮的点击事件
    private void initBtn() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                //更新sharepreference的状态，将第一次进入的应用标志改为false
                spUtils.setBoolean(getApplicationContext(),"isFirstEnter",false);
                finish();
            }
        });
    }

    // 监听layout方法结束的事件,位置确定好之后再获取圆点间距
    private void layoutListener() {
        redPoint.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //layout方法结束时回调
                redPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);//移除监听，避免重复回调

                // 计算两个圆点的距离
                // 移动距离=第二个圆点left值 - 第一个圆点left值
                mPointDis = pointContainer.getChildAt(1).getLeft()
                        - pointContainer.getChildAt(0).getLeft();
            }
        });
    }


    //给viewPager设置监听，用来实时显示小红点的位置
    private void initRedPoint() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //当页面滑动过程中回调  position :当前位置    positionOffset  ：移动偏移百分比

                //通过更新小红点的布局参数来更新小红点的位置,
                int dis = (int) (mPointDis * positionOffset) + position * mPointDis;//小红点移动的距离

                RelativeLayout.LayoutParams params =
                        (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
                //修改左边距
                params.leftMargin = dis;

                //重新设置布局参数
                redPoint.setLayoutParams(params);

            }

            @Override
            public void onPageSelected(int position) {
            //某个页面被选中
                //当第三个页面被选中时，显示button按钮
                if (position == viewArrayList.size()-1){
                    //显示button
                    bt.setVisibility(View.VISIBLE);
                }else {
                    //隐藏button
                    bt.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            //当页面状态发生变化
            }
        });

    }
    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        pointContainer = (LinearLayout) findViewById(R.id.ll_point_container);
        redPoint = (ImageView) findViewById(R.id.iv_red_point);
        bt = (Button) findViewById(R.id.btn_start);
    }

    private void initData() {
        //存储引导页图片的id
        mImageIds = new int[]{R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
        //根据id获取对应的图片
        ImageView imageView;
        ImageView   point;
        viewArrayList = new ArrayList<ImageView>();
        for (int i=0;i<mImageIds.length;i++){
           imageView = new ImageView(getApplicationContext());
            imageView.setBackgroundResource(mImageIds[i]);//将对应的图片填充为背景
            viewArrayList.add(imageView);

            //初始化小圆点
            point = new ImageView(getApplicationContext());
            point.setImageResource(R.drawable.shape_point_gray);//设置图片，根据图片大小添加

            //初始化布局参数，宽高包裹内容，父控件是谁就声明谁的布局参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            //设置圆点间的间距
            if (i>0){
                //从第二个点设置左边距
                params.leftMargin = 15;
            }

            pointContainer.addView(point,params);//给容器添加圆点

        }


    }

    private void initAdapter() {
        mViewPager.setAdapter(new MyGuideAdaper());
    }

    class MyGuideAdaper extends PagerAdapter{

        @Override
        public int getCount() {
            return viewArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //初始化item布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = viewArrayList.get(position);
            container.addView(view);
            return view;
        }

        //销毁item布局
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            /*super.destroyItem(container, position, object);*/
            container.removeView((View) object);
        }
    }

}
