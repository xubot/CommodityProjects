package com.example.administrator.commodityproject.Activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.commodityproject.Fragment.ClassifyFragment;
import com.example.administrator.commodityproject.Fragment.CommunityFragment;
import com.example.administrator.commodityproject.Fragment.HomeFragment;
import com.example.administrator.commodityproject.Fragment.ShopFragment;
import com.example.administrator.commodityproject.Fragment.UserFragment;
import com.example.administrator.commodityprojects.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private HomeFragment homeFragment;
    private ClassifyFragment classifyFragment;
    private CommunityFragment communityFragment;
    private ShopFragment shopFragment;
    private UserFragment userFragment;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private int[] clickBefore=new int[]{R.drawable.bottom_tab_home_normal,R.drawable.bottom_tab_classify_normal,R.drawable.check_normal_2,R.drawable.bottom_tab_shopping_normal,R.drawable.bottom_tab_user_normal};
    private int[] clickAfter=new int[]{R.drawable.bottom_tab_home_selected,R.drawable.bottom_tab_classify_selected,R.drawable.check_checked_2,R.drawable.bottom_tab_shopping_selected,R.drawable.bottom_tab_user_selected};
    private LinearLayout ll_home,ll_classify, ll_community, ll_shop, ll_uesr;
    private TextView tv_home, tv_classify, tv_community, tv_shop, tv_uesr;
    private ImageView iv_home,iv_classify, iv_community, iv_shop, iv_uesr;
    private List<LinearLayout> linearLayoutList=new ArrayList<>();
    private List<ImageView> imageViewList=new ArrayList<>();
    private List<TextView> textViewList=new ArrayList<>();
    private Fragment fragmentData;
    private int type=0;
    private int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflateView();
        //初次页面
        if (homeFragment==null){
            homeFragment =new HomeFragment();
        }
        addFragment(homeFragment);
        setBackground(0);
    }
    //布局控件
    public void inflateView() {
        //得到控件(LinearLayout的控件)
        ll_home = (LinearLayout) findViewById(R.id.ll_home);
        ll_classify = (LinearLayout) findViewById(R.id.ll_classify);
        ll_community = (LinearLayout) findViewById(R.id.ll_community);
        ll_shop = (LinearLayout) findViewById(R.id.ll_shop);
        ll_uesr = (LinearLayout) findViewById(R.id.ll_uesr);
        //得到下面汉字的控件
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_classify = (TextView) findViewById(R.id.tv_classify);
        tv_community = (TextView) findViewById(R.id.tv_community);
        tv_shop = (TextView) findViewById(R.id.tv_shop);
        tv_uesr = (TextView) findViewById(R.id.tv_uesr);
        //得到下面图片的控件
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_classify = (ImageView) findViewById(R.id.iv_classify);
        iv_community = (ImageView) findViewById(R.id.iv_community);
        iv_shop = (ImageView) findViewById(R.id.iv_shop);
        iv_uesr = (ImageView) findViewById(R.id.iv_uesr);
        addControl();
    }
    //添加布局和控件
    public void addControl() {
        //向集合中赋值（LayoutList）
        linearLayoutList.add(ll_home);
        linearLayoutList.add(ll_classify);
        linearLayoutList.add(ll_community);
        linearLayoutList.add(ll_shop);
        linearLayoutList.add(ll_uesr);
        //向集合中赋值（imageView）
        imageViewList.add(iv_home);
        imageViewList.add(iv_classify);
        imageViewList.add(iv_community);
        imageViewList.add(iv_shop);
        imageViewList.add(iv_uesr);
        //向集合中赋值（ textView）
        textViewList.add(tv_home);
        textViewList.add(tv_classify);
        textViewList.add(tv_community);
        textViewList.add(tv_shop);
        textViewList.add(tv_uesr );
        //给当前布局设置点击监听
        for(LinearLayout lll:linearLayoutList)
        {
            lll.setOnClickListener(this);
        }
    }
    //开启事务
    private void addFragment(Fragment fragment) {
        //得到fragment的管理类
        manager = getSupportFragmentManager();
        //开启一个事务
        transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.fragment_slide_left_enter,R.anim.fragment_slide_right_exit);
        //赋值
        if (fragmentData!=null){
            transaction.hide(fragmentData);
        }if(!fragment.isAdded()){
            //添加当前fragment
            transaction.add(R.id.fl,fragment);
        }
        //显示当前事务
        transaction.show(fragment);
        //提交事务
        transaction.commit();
        //给空槽赋值
        fragmentData=fragment;
    }
    //每个页面的点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                index=0;
                //初次页面
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                addFragment(homeFragment);
                setBackground(0);
                type=index;
                break;

            case R.id.ll_classify:
                index=1;
                //初次页面
                if (classifyFragment == null) {
                    classifyFragment = new ClassifyFragment();
                }
                addFragment(classifyFragment);
                setBackground(1);
                type=index;
                break;
            case R.id.ll_community:
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment();
                }
                addFragment(communityFragment);
                setBackground(2);
                break;
            case R.id.ll_shop:
                if (shopFragment == null) {
                    shopFragment = new ShopFragment();
                }
                addFragment(shopFragment);
                setBackground(3);
                break;
            case R.id.ll_uesr :
                if (userFragment == null) {
                    userFragment = new UserFragment();
                }
                addFragment(userFragment);
                setBackground(4);
                break;
        }
    }
    //设置颜色（点击时）
    public void setBackground(int index) {
        for(int i=0;i<linearLayoutList.size();i++)
        {
            if(i==index)
            {
                imageViewList.get(i).setImageResource(clickAfter[i]);
                textViewList.get(i).setTextColor(Color.RED);
            }
            else
            {
                imageViewList.get(i).setImageResource(clickBefore[i]);
                textViewList.get(i).setTextColor(Color.BLACK);
            }
        }
    }
}
