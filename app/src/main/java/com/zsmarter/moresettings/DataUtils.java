package com.zsmarter.moresettings;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 保存数据工具类
 */
public class DataUtils {

    public static final String DEFAULT_SP_NAME = "DEFAULT_SP_NAME";
    private static String TAG = "DataUtils";

    public static <T> void saveData(List<T> data, String spName, String key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        //序列化确定类型的字符串生成json
        String jsonString = gson.toJson(data);
        Log.d(TAG, jsonString + "jsonString");
        editor.putString(key, jsonString);
        editor.apply();
    }

    public static List<User> getData(String spName, String key, Context context) {
        List<User> data = new ArrayList<>();
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String jsonString = preferences.getString(key, null);
        if (jsonString == null) {
            return data;
        }
        Log.e("JSON", jsonString + "ssss" + new TypeToken<List<User>>() {
        }.getType());
        Gson gson = new Gson();
        //Gson字符串数组转成List对象
        data = gson.fromJson(jsonString, new TypeToken<List<User>>() {
        }.getType());
        return data;
    }
}
