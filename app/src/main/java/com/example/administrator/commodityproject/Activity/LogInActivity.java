package com.example.administrator.commodityproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.commodityproject.Bean.RegisterdBean;
import com.example.administrator.commodityprojects.R;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tv_register;
    private EditText log_user;
    private EditText log_pwd;
    private Button log_login;
    private SharedPreferences sharedPreferencesp;
    private SharedPreferences.Editor edit;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        sharedPreferencesp = getSharedPreferences("user_id", MODE_PRIVATE);
        edit = sharedPreferencesp.edit();
        edit.putBoolean("flag",flag);
        initView();
    }
    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        log_user = (EditText) findViewById(R.id.log_user);
        log_pwd = (EditText) findViewById(R.id.log_pwd);
        log_login = (Button) findViewById(R.id.log_login);
        tv_register.setOnClickListener(this);
        log_login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:
                Intent intent = new Intent(LogInActivity.this, RegisteredActivity.class);
                startActivity(intent);
                finish();
                break;
            case  R.id.log_login:
                checkLogin();
                break;
        }
    }

    private void checkLogin() {
        OkHttpClient okHttpClient = new OkHttpClient();
        String path="http://169.254.217.5:8080/bullking1/login";
        final String user = log_user.getText().toString().trim();
        String pwd = log_pwd.getText().toString().trim();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("name",user);
        builder.add("pwd",pwd);
        Request build = new Request.Builder().url(path).post(builder.build()).build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String vaule = response.body().string();
                Log.d("zzzz", vaule);
                Gson gson = new Gson();
                RegisterdBean registerdBean = gson.fromJson(vaule, RegisterdBean.class);
                final String dataStr = registerdBean.getDataStr();
                final int id = registerdBean.getId();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(dataStr.equals("login succeed")) {
                            Toast.makeText(LogInActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Log.d("zzzz", id+"");
                            Intent intent = new Intent();
                            intent.putExtra("name",user);
                            intent.putExtra("img",R.drawable.a);
                            setResult(1002,intent);
                            flag=true;
                            edit.putBoolean("flag",flag);
                            Log.d("zzz", "成功"+flag);
                            finish();
                        }else {
                            Toast.makeText(LogInActivity.this, "没有此账号，请注册", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LogInActivity.this, RegisteredActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
