package com.example.administrator.commodityproject.Adapter;

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

import static android.R.id.list;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/18 10:00
 */

public class MyItemThreeAdater extends RecyclerView.Adapter<MyItemThreeAdater.MyRecyclerHolder> {
    private final Context context;
    private final List<GoodsBean> goodsListBeanList;
    public MyItemThreeAdater(Context context, List<GoodsBean> goodsListBeanList) {

        this.context = context;
        this.goodsListBeanList = goodsListBeanList;
    }

    @Override
    public MyRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyRecyclerHolder holder=new MyRecyclerHolder(LayoutInflater.from(context).inflate(R.layout.item_three_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerHolder holder, int position) {
        ImageLoader.getInstance().displayImage(goodsListBeanList.get(position).getImg(),holder.img);
        holder.tv.setText(goodsListBeanList.get(position).getName());
        holder.price.setText(goodsListBeanList.get(position).getShop_price());
    }

    @Override
    public int getItemCount() {
        return goodsListBeanList.size();
    }

    public class MyRecyclerHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView img;
        TextView price;
        public MyRecyclerHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tv = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
