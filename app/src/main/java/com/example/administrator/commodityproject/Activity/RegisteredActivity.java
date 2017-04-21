package com.example.administrator.commodityproject.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.commodityproject.Bean.RegisterdBean;
import com.example.administrator.commodityproject.Utils.DataInterface;
import com.example.administrator.commodityproject.Utils.Okhttp;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class RegisteredActivity extends AppCompatActivity implements DataInterface{
    private Button bt_regst;
    private EditText reg_user;
    private EditText reg_pwd;
    private List<RegisterdBean> registerdBeanList=new ArrayList<>();
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        sp = getSharedPreferences("user_id", MODE_PRIVATE);
        initView();
    }
    private void initView() {
        reg_user = (EditText) findViewById(R.id.reg_user);
        reg_pwd = (EditText) findViewById(R.id.reg_pwd);
        bt_regst = (Button) findViewById(R.id.bt_regst);
        bt_regst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names = reg_user.getText().toString();
                String paws = reg_pwd.getText().toString();
                getRegies(names,paws);
                Intent in =new Intent(RegisteredActivity.this,LogInActivity.class);
                startActivity(in);
            }
        });
    }

    private void getRegies(String names, String paws) {
        String path="http://169.254.217.5:8080/bullking1/register?name="+names+"&pwd="+paws+"";
        Okhttp.okhttp(path,this);
    }

    @Override
    public void setData(String vaule) {
        Gson gson = new Gson();
        RegisterdBean registerdBean = gson.fromJson(vaule, RegisterdBean.class);
        final String dataStr = registerdBean.getDataStr();
        final int id = registerdBean.getId();
        registerdBeanList.add(new RegisterdBean(dataStr,id));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(dataStr.equals("register succeed")) {
                    Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putInt("id",id);
                } else {
                    Toast.makeText(RegisteredActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
