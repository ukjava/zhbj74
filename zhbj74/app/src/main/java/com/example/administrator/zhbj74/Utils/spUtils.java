package com.example.administrator.zhbj74.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * sharepreference的工具类封装
 * Created by Administrator on 2018/11/4.
 */

public class spUtils {

    public static Boolean getBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key,value);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,value).commit();

    }

    public static String getString(Context ctx, String key, String value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);

        return sharedPreferences.getString(key,value);
    }

    public static void setString(Context ctx, String key, String value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).commit();

    }


    public static Integer getInt(Context ctx, String key, int value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);

        return sharedPreferences.getInt(key,value);
    }

    public static void setInt(Context ctx, String key, int value) {
        SharedPreferences sharedPreferences =
                ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key,value).commit();

    }

}
