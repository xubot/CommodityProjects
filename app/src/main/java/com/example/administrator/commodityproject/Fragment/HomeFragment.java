package com.example.administrator.commodityproject.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.commodityproject.Activity.WebViewActivity;
import com.example.administrator.commodityproject.Adapter.HomeGridViewAdapter;
import com.example.administrator.commodityproject.Bean.Ab5Bean;
import com.example.administrator.commodityproject.Bean.HomeBean;
import com.example.administrator.commodityproject.Utils.DataInterface;
import com.example.administrator.commodityproject.Utils.GlideImageLoader;
import com.example.administrator.commodityproject.Utils.Okhttp;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/12 15:14
 */

public class HomeFragment extends Fragment implements DataInterface {
    private List<String> imgList=new ArrayList<>();
    private List<String> urlList=new ArrayList<>();
    private List<Ab5Bean> ab5BeanList=new ArrayList<>();
    private Banner banner;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, null);
        banner = (Banner) view.findViewById(R.id.banner);
        gridView = (GridView) view.findViewById(R.id.gv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String path="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
        Okhttp.okhttp(path, HomeFragment.this);
        gridView.setAdapter(new HomeGridViewAdapter(getActivity(),ab5BeanList));
    }

    @Override
    public void setData(String vaule) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(vaule, HomeBean.class);
        List<HomeBean.DataBean.Ad1Bean> ad1 = homeBean.getData().getAd1();
        List<HomeBean.DataBean.Ad5Bean> ad5 = homeBean.getData().getAd5();
        for(HomeBean.DataBean.Ad1Bean ab1:ad1)
        {
            String image = ab1.getImage();
            String url = ab1.getAd_type_dynamic_data();
            imgList.add(image);
            urlList.add(url);
        }
        for(HomeBean.DataBean.Ad5Bean ab5:ad5)
        {
            String image = ab5.getImage();
            String title = ab5.getTitle();
            Log.d("zzz", title + "     " + image);
            Ab5Bean ab5Bean = new Ab5Bean(title, image);
            ab5BeanList.add(ab5Bean);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBanner();
            }
        });
    }

    private void setBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(imgList);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", urlList.get(position));
                startActivity(intent);
            }
        });
    }
}
