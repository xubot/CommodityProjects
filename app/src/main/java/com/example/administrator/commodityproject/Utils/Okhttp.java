package com.example.administrator.commodityproject.Utils;

import android.util.Log;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;




/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/12 09:31
 */
public class Okhttp {
    public  static void okhttp(String path, final DataInterface dataInterface)
    {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request=new Request.Builder()
                .url(path)
                .build();
        Log.d("zzz", request.toString());
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                dataInterface.setData(string);
            }
        });
    }
}
