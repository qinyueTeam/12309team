package com.qinyue.monitor.util;

import com.google.gson.Gson;
import com.qinyue.monitor.base.BaseBean;
import com.qinyue.monitor.base.BaseListBean;
import com.qinyue.monitor.base.ParameterizedTypeImpl;

import java.lang.reflect.Type;


public class JsonUtils {
   public Gson gson;
   private static JsonUtils jsonUtils;
   private JsonUtils(){
       gson= new Gson();
    }
    public static JsonUtils getInstance() {

        if (jsonUtils == null) {
            synchronized (JsonUtils.class) {
                if (jsonUtils == null) {
                    jsonUtils = new JsonUtils();
                }
            }
        }
        return jsonUtils;
    }
    public  <T> BaseBean<T> fromJsonObject(String reader, Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(BaseBean.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }

    public  <T> BaseListBean<T> fromJsonArray(String reader, final Class<T> clazz) {
        Type type = new ParameterizedTypeImpl(BaseListBean.class, new Class[]{clazz});
        return gson.fromJson(reader, type);
    }
}
