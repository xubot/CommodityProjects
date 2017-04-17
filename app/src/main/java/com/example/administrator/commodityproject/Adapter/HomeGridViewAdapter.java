package com.example.administrator.commodityproject.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.administrator.commodityproject.Bean.Ab5Bean;
import com.example.administrator.commodityprojects.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/13 18:34
 */

public class HomeGridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<Ab5Bean> ab5BeanList;

    public HomeGridViewAdapter(Context context, List<Ab5Bean> ab5BeanList) {
        this.context = context;
        this.ab5BeanList = ab5BeanList;
    }

    @Override
    public int getCount() {
        return ab5BeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= View.inflate(context, R.layout.home_gv,null);
        ImageView imageView= (ImageView) convertView.findViewById(R.id.iv);
        ImageLoader.getInstance().displayImage(ab5BeanList.get(position).getImg(),imageView);
        TextView textView= (TextView) convertView.findViewById(R.id.tv);
        textView.setText(ab5BeanList.get(position).getTitle());
        return convertView;
    }
}
