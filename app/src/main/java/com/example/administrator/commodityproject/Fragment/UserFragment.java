package com.example.administrator.commodityproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.commodityproject.Activity.LogInActivity;
import com.example.administrator.commodityprojects.R;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/12 15:14
 */

public class UserFragment extends Fragment {
    private Button user_log;
    private ImageView user_img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        user_log = (Button) view.findViewById(R.id.user_log);
        user_img = (ImageView) view.findViewById(R.id.user_img);
        user_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogInActivity.class);
                startActivityForResult(intent,1001);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name = data.getStringExtra("name");
        int img = data.getIntExtra("img", R.mipmap.ic_launcher);
        user_log.setText(name);
        user_img.setImageResource(img);
    }
}
