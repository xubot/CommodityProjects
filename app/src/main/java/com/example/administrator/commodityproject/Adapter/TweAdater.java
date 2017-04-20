package com.example.administrator.commodityproject.Adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.commodityprojects.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/17 19:42
 */

public class TweAdater extends RecyclerView.Adapter<TweAdater.MyRecyclerHolder>{
    private final FragmentActivity activity;
    private final List<String> imageList;

    public TweAdater(FragmentActivity activity, List<String> imageList) {
        this.activity = activity;
        this.imageList = imageList;
    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_twe, parent, false);
        MyRecyclerHolder myRecyclerHolder = new MyRecyclerHolder(view);
        myRecyclerHolder.img= (ImageView) view.findViewById(R.id.img);
        return myRecyclerHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerHolder holder, int position) {
        ImageLoader.getInstance().displayImage(imageList.get(position),holder.img);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
    public static class MyRecyclerHolder extends RecyclerView.ViewHolder{
        public MyRecyclerHolder(View itemView) {
            super(itemView);
        }
        //设置得到控件
        ImageView img;
    }
}
