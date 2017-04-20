package com.example.administrator.commodityproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.commodityproject.Activity.GalleryActivity;
import com.example.administrator.commodityproject.Activity.Main2Activity;
import com.example.administrator.commodityproject.Activity.WebViewActivity;
import com.example.administrator.commodityproject.Adapter.GalleryAdapter;
import com.example.administrator.commodityproject.Adapter.HomeGridViewAdapter;
import com.example.administrator.commodityproject.Adapter.MasonryAdapter;
import com.example.administrator.commodityproject.Adapter.MyItemThreeAdater;
import com.example.administrator.commodityproject.Adapter.SpacesItemDecoration;
import com.example.administrator.commodityproject.Adapter.TweAdater;
import com.example.administrator.commodityproject.Bean.Ab5Bean;
import com.example.administrator.commodityproject.Bean.GoodsBean;
import com.example.administrator.commodityproject.Bean.HomeBean;
import com.example.administrator.commodityproject.Utils.DataInterface;
import com.example.administrator.commodityproject.Utils.GlideImageLoader;
import com.example.administrator.commodityproject.Utils.Okhttp;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
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
    private List<GoodsBean> goodsListBeanXList=new ArrayList<>();
    private List<GoodsBean> goodsListBeanList=new ArrayList<>();
    private List<GoodsBean> goodsList=new ArrayList<>();
    private List<String> imageList=new ArrayList<>();
    private List<String> image1List=new ArrayList<>();
    private Banner banner;
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, null);
        banner = (Banner) view.findViewById(R.id.banner);
        gridView = (GridView) view.findViewById(R.id.gv);
        TextView more= (TextView) view.findViewById(R.id.more);
        RecyclerView recyclerView_one= (RecyclerView) view.findViewById(R.id.rv_one);
        RecyclerView recyclerView_twe= (RecyclerView) view.findViewById(R.id.rv_twe);
        RecyclerView recyclerView_three= (RecyclerView) view.findViewById(R.id.rv_three);
        RecyclerView recyclerView_frou= (RecyclerView) view.findViewById(R.id.rv_frou);
        LinearLayoutManager linearLayoutManager_one = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager_twe = new LinearLayoutManager(getActivity());
        LinearLayoutManager linearLayoutManager_three = new LinearLayoutManager(getActivity());
        linearLayoutManager_one.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager_twe.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_frou.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置初始值
        recyclerView_one.setLayoutManager(linearLayoutManager_one);
        recyclerView_twe.setLayoutManager(linearLayoutManager_twe);
        recyclerView_three.setLayoutManager(linearLayoutManager_three);
        //设置适配器
        final GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), goodsListBeanXList);
        TweAdater tweAdater = new TweAdater(getActivity(), imageList);
        MyitemAdapter myitemAdapter = new MyitemAdapter();
        recyclerView_one.setAdapter(galleryAdapter);
        recyclerView_twe.setAdapter(tweAdater);
        recyclerView_three.setAdapter(myitemAdapter);
        //设置适配器
        MasonryAdapter endadapter=new MasonryAdapter(goodsList);
        recyclerView_frou.setAdapter(endadapter);
        //设置item之间的间隔
        //得到条目的装饰对象
        SpacesItemDecoration decoration=new SpacesItemDecoration(16);
        //给控件添加条目装饰
        recyclerView_frou.addItemDecoration(decoration);
        endadapter.setOnItemClickListener(new MasonryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), GalleryActivity.class);
                intent.putExtra("id",goodsList.get(position).getId());
                startActivity(intent);
            }
        });
        //加载更多的点击监听
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Main2Activity.class));
            }
        });
        //gridView的点击监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url",ab5BeanList.get(position).getUrl());
                startActivity(intent);
            }
        });
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
        List<HomeBean.DataBean.BestSellersBean> bestSellers = homeBean.getData().getBestSellers();
        List<HomeBean.DataBean.DefaultGoodsListBean> defaultGoodsList = homeBean.getData().getDefaultGoodsList();
        List<HomeBean.DataBean.SubjectsBean> subjects = homeBean.getData().getSubjects();
        for(HomeBean.DataBean.SubjectsBean sj:subjects) {
            String image = sj.getImage();
            image1List.add(image);
            List<HomeBean.DataBean.SubjectsBean.GoodsListBean> goodsList = sj.getGoodsList();
            for(HomeBean.DataBean.SubjectsBean.GoodsListBean gl:goodsList) {
                String shop_price = gl.getShop_price()+"";
                String goods_name = gl.getGoods_name();
                String goods_img = gl.getGoods_img();
                String id = gl.getId();
                goodsListBeanList.add(new GoodsBean(id,goods_img,goods_name,shop_price));
            }
        }
        for(HomeBean.DataBean.DefaultGoodsListBean dg:defaultGoodsList) {
            String goods_img = dg.getGoods_img();
            imageList.add(goods_img);
            String goods_name = dg.getGoods_name();
            String shop_price = dg.getShop_price()+"";
            String id = dg.getId();
            goodsList.add(new GoodsBean(id,goods_img,goods_name,shop_price));
        }
        for(HomeBean.DataBean.BestSellersBean sellersBean:bestSellers) {
            List<HomeBean.DataBean.BestSellersBean.GoodsListBeanX> goodsList = sellersBean.getGoodsList();
            for(HomeBean.DataBean.BestSellersBean.GoodsListBeanX gl:goodsList) {
                String goods_img = gl.getGoods_img();
                String goods_name = gl.getGoods_name();
                String shop_price = gl.getShop_price()+"";
                String id = gl.getId();
                GoodsBean goodsBean = new GoodsBean(id,goods_img, goods_name, shop_price);
                goodsListBeanXList.add(goodsBean);
            }
        }
        for(HomeBean.DataBean.Ad1Bean ab1:ad1) {
            String image = ab1.getImage();
            String url = ab1.getAd_type_dynamic_data();
            imgList.add(image);
            urlList.add(url);
        }
        for(HomeBean.DataBean.Ad5Bean ab5:ad5){
            String image = ab5.getImage();
            String title = ab5.getTitle();
            String ad_type_dynamic_data = ab5.getAd_type_dynamic_data();
            Log.d("zzz", title + "     " + image);
            Ab5Bean ab5Bean = new Ab5Bean(title, image,ad_type_dynamic_data);
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

    public class MyitemAdapter extends RecyclerView.Adapter<MyitemAdapter.MyViewHoler> {
        @Override
        public MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHoler holer = new MyViewHoler(LayoutInflater.from(getActivity()).inflate(R.layout.item_three, parent, false));
            return holer;
        }
        @Override
        public void onBindViewHolder(MyViewHoler holder, int position) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.rev.setLayoutManager(linearLayoutManager);
            MyItemThreeAdater myItemThreeAdater = new MyItemThreeAdater(getActivity(),goodsListBeanList);
            holder.rev.setAdapter(myItemThreeAdater);
            ImageLoader.getInstance().displayImage(image1List.get(position),holder.imager);
        }
        @Override
        public int getItemCount() {
            return image1List.size();
        }

        public class MyViewHoler extends RecyclerView.ViewHolder {
            ImageView imager;
            RecyclerView rev;
            public MyViewHoler(View itemView) {
                super(itemView);
                imager = (ImageView) itemView.findViewById(R.id.imager);
                rev = (RecyclerView) itemView.findViewById(R.id.rev);
            }
        }
    }

}
