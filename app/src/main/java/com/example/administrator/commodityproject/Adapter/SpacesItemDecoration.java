package com.example.administrator.commodityproject.Adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/18 13:23
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    //参数一：矩形接收输出。
    //参数二：要装饰的视图
    //参数三：RecyclerView 条目装饰
    //参数四：RecyclerView的当前状态。
    //得到条目离开当前位置的设置的方法
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
