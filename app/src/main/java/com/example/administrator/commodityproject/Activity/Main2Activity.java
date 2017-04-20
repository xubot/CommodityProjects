package com.example.administrator.commodityproject.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.administrator.commodityproject.Adapter.MasonryAdapter;
import com.example.administrator.commodityproject.Adapter.SpacesItemDecoration;
import com.example.administrator.commodityproject.Bean.GoodsBean;
import com.example.administrator.commodityproject.Bean.HomeBean;
import com.example.administrator.commodityproject.Utils.DataInterface;
import com.example.administrator.commodityproject.Utils.Okhttp;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements DataInterface {
    private List<GoodsBean> goodsBeen=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String path="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";
        Okhttp.okhttp(path, Main2Activity.this);
    }

    @Override
    public void setData(String vaule) {
        Gson gson = new Gson();
        HomeBean homeBean = gson.fromJson(vaule, HomeBean.class);
        List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList = homeBean.getData().getDefaultGoodsList();
        for(HomeBean.DataBean.DefaultGoodsListBean dg:defaultGoodsList) {
            String goods_img = dg.getGoods_img();
            String goods_name = dg.getGoods_name();
            String shop_price = dg.getShop_price()+"";
            String id = dg.getId();
            goodsBeen.add(new GoodsBean(id,goods_img,goods_name,shop_price));
            Log.d("zzz1",goods_img+"   "+goods_name+"    "+shop_price);
        }
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RecyclerView  recyclerView= (RecyclerView) findViewById(R.id.rview);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                //设置适配器
                MasonryAdapter adapter=new MasonryAdapter(goodsBeen);
                recyclerView.setAdapter(adapter);
                //设置item之间的间隔
                //得到条目的装饰对象
                SpacesItemDecoration decoration=new SpacesItemDecoration(16);
                //给控件添加条目装饰
                recyclerView.addItemDecoration(decoration);
            }
        });
    }
}
