package com.myworld.android.myanim.Utils;

import com.google.gson.Gson;
import com.myworld.android.myanim.Bean.JavaBean;

/**
 * Created by Administrator on 2017/11/23.
 */

public class JsonUtils {
    public JavaBean getData(String json) {
        //创建Gson对象
        Gson gson = new Gson();
        JavaBean data = gson.fromJson(json, JavaBean.class);
        return data;
    }
}
