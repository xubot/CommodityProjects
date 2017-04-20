package com.example.administrator.commodityproject.Adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * 时间：2017/4/18 13:24
 */

public class MasonryAdapter extends RecyclerView.Adapter<MasonryAdapter.MasonryView>{

    private List<GoodsBean> list;

    public MasonryAdapter(List<GoodsBean> list) {
        this.list = list;
    }
    //声明接口
    public OnItemClickListener itemClickListener;
    //定义条目点击事件
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    //对外提供两个访问的set方法
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    @Override
    //得到布局文件的方法
    public MasonryView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_frou, viewGroup, false);
        final MasonryView masonryView = new MasonryView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = masonryView.getLayoutPosition();
                //注册点击监听
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(v, position);
                }
            }
        });
        return masonryView;
    }

    @Override
    //参数一： 项目在给定位置的数据集。（得到控件）
    //参数二：他项目的位置在适配器的数据集（每一个角标）
    //赋值的方法
    public void onBindViewHolder(MasonryView masonryView, int position) {
        ImageLoader.getInstance().displayImage(list.get(position).getImg(),masonryView.imageView);
        masonryView.title.setText(list.get(position).getName());
        masonryView.pricce.setText(list.get(position).getShop_price());
    }

    @Override
    public int getItemCount() {
        Log.d("zzz1", list.size()+"");
        return list.size();

    }

    //得到控件的内部类继承RecyclerView里面的控件持有者
    public static class MasonryView extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView title;
        TextView pricce;

        public MasonryView(View itemView){
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.img );
            title= (TextView) itemView.findViewById(R.id.title);
            pricce= (TextView) itemView.findViewById(R.id.price);
        }
    }
}
