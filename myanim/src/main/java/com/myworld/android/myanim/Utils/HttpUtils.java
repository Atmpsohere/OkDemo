package com.myworld.android.myanim.Utils;

import android.content.Context;
import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/11/23.
 */

public class HttpUtils {
    private Context context;
    private LoadData load;
    private Handler handler = new Handler();

    public HttpUtils(Context context, LoadData load) {
        this.context = context;
        this.load = load;
    }

    //将get请求和post请求分别封装成两个方法
    public void getResult(String path) {
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(path).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(data);
                    }
                });
            }
        });
    }

    public void postRequest(String path, HashMap<String, String> map) {
        //首先创建okhttpclient对象
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000, TimeUnit.SECONDS).build();
        //创建formbody对象
        FormBody.Builder builder = new FormBody.Builder();
        //通过增强for循环添加
        for (String key : map.keySet()) {
            builder.add(key, map.get(key));
        }
        //创建请求对象
        final Request request = new Request.Builder().url(path).post(builder.build()).build();
        //创建Call对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        load.load(data);
                    }
                });
            }
        });

    }

    public interface LoadData {
        void load(String data);
    }
}
