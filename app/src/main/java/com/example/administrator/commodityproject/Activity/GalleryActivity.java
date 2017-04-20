package com.example.administrator.commodityproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.commodityproject.Bean.ActivityBean;
import com.example.administrator.commodityproject.Bean.DetailsBean;
import com.example.administrator.commodityproject.Utils.DataInterface;
import com.example.administrator.commodityproject.Utils.GlideImageLoader;
import com.example.administrator.commodityproject.Utils.Okhttp;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import java.util.ArrayList;
import java.util.List;

public class GalleryActivity extends AppCompatActivity implements DataInterface,View.OnClickListener{

    private Banner banner;
    private List<String> stringList=new ArrayList<>();
    private List<ActivityBean> activityBeanList=new ArrayList<>();
    private TextView shop_name;
    private TextView shop_price;
    private TextView shipping_fee;
    private TextView sales_volume;
    private TextView collect_count;
    private TextView activity_one;
    private TextView activity_twe;
    private TextView activity_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        banner = (Banner) findViewById(R.id.br);
        shop_name = (TextView) findViewById(R.id.name);
        shop_price = (TextView) findViewById(R.id.price);
        shipping_fee = (TextView) findViewById(R.id.shipping_fee);
        sales_volume = (TextView) findViewById(R.id.sales_volume);
        collect_count = (TextView) findViewById(R.id.collect_count);
        //购物车和支付
        Button addcar= (Button) findViewById(R.id.addcar);
        Button pay= (Button) findViewById(R.id.pay);

        activity_one = (TextView) findViewById(R.id.Activity_one);
        activity_twe = (TextView) findViewById(R.id.Activity_twe);
        activity_three = (TextView) findViewById(R.id.Activity_three);
        activity_one.setOnClickListener(this);
        activity_twe.setOnClickListener(this);
        activity_three.setOnClickListener(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String path="http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+id+"";
        Okhttp.okhttp(path,this);
    }

    @Override
    public void setData(String vaule) {
        Gson gson = new Gson();
        final DetailsBean detailsBean = gson.fromJson(vaule, DetailsBean.class);
        List<DetailsBean.DataBean.GoodsBean.GalleryBean> gallery = detailsBean.getData().getGoods().getGallery();
        List<DetailsBean.DataBean.ActivityBean> activity = detailsBean.getData().getActivity();
        for(DetailsBean.DataBean.ActivityBean a:activity)
        {
            String description = a.getDescription();
            String title = a.getTitle();
            activityBeanList.add(new ActivityBean(title,description));
        }
        for(DetailsBean.DataBean.GoodsBean.GalleryBean gb:gallery)
        {
            String normal_url = gb.getNormal_url();
            stringList.add(normal_url);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBanner();
                getData(detailsBean,activityBeanList);
            }
        });
    }

    private void setBanner() {
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(stringList);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }
    public void getData(DetailsBean detailsBean,List<ActivityBean> activityBeanList) {
        shop_name.setText(detailsBean.getData().getGoods().getGoods_name());
        shop_price.setText("￥"+detailsBean.getData().getGoods().getShop_price());
        shipping_fee.setText("运费￥"+detailsBean.getData().getGoods().getShipping_fee());
        sales_volume.setText("销量 "+detailsBean.getData().getGoods().getSales_volume());
        collect_count.setText("收藏 "+detailsBean.getData().getGoods().getCollect_count());
        activity_one.setText(activityBeanList.get(0).getTitle());
        activity_twe.setText(activityBeanList.get(1).getTitle());
        activity_three.setText(activityBeanList.get(2).getTitle());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Activity_one :
                Intent intent_one = new Intent(GalleryActivity.this, WebViewActivity.class);
                intent_one.putExtra("url",activityBeanList.get(0).getDescription());
                startActivity(intent_one);
                break;
            case R.id.Activity_twe :
                Intent intent_twe = new Intent(GalleryActivity.this, WebViewActivity.class);
                intent_twe.putExtra("url",activityBeanList.get(0).getDescription());
                startActivity(intent_twe);
                break;
            case R.id.Activity_three :
                Intent intent_three = new Intent(GalleryActivity.this, WebViewActivity.class);
                intent_three.putExtra("url",activityBeanList.get(0).getDescription());
                startActivity(intent_three);
                break;
        }
    }
}
