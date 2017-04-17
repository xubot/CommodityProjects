package com.example.administrator.commodityproject.Fragment;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.commodityproject.Bean.GoodsBean;
import com.example.administrator.commodityprojects.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/17 14:25
 */

class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyRecyclerHolder>{

    private final FragmentActivity homeFragment;
    private final List<GoodsBean> goodsListBeanXList;

    public GalleryAdapter(FragmentActivity homeFragment, List<GoodsBean> goodsListBeanXList) {
        this.homeFragment = homeFragment;
        this.goodsListBeanXList = goodsListBeanXList;
    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(homeFragment, R.layout.item, null);
        MyRecyclerHolder myRecyclerHolder = new MyRecyclerHolder(inflate);
        myRecyclerHolder.img= (ImageView) inflate.findViewById(R.id.img);
        myRecyclerHolder.tv_title= (TextView) inflate.findViewById(R.id.tv_title);
        myRecyclerHolder.tv_price= (TextView) inflate.findViewById(R.id.tv_price);
        return myRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerHolder holder, int position) {
        ImageLoader.getInstance().displayImage(goodsListBeanXList.get(position).getImg(),holder.img);
        holder.tv_title.setText(goodsListBeanXList.get(position).getName());
        holder.tv_price.setText(goodsListBeanXList.get(position).getShop_price());
    }


    @Override
    public int getItemCount() {
        return goodsListBeanXList.size();
    }
    public static class MyRecyclerHolder extends RecyclerView.ViewHolder{
        public MyRecyclerHolder(View itemView) {
            super(itemView);
        }
        //设置得到控件
        ImageView img;
        TextView tv_title;
        TextView tv_price;
    }
}
