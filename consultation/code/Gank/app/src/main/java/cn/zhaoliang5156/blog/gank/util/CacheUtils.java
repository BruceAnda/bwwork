package cn.zhaoliang5156.blog.gank.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright (C), 2015-2019, 八维集团
 * Author: zhaoliang
 * Date: 2019/5/21 10:40 AM
 * Description: 缓存工具类，缓存软件的一些数据和参数
 */
public class CacheUtils {

    /**
     * 读取软件参数
     *
     * @param context 上下文
     * @param key     键
     * @return 读取到的值
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    /**
     * 保存软件参数
     *
     * @param context 上下文
     * @param key     键
     * @param value   存入的值
     */
    public static void pubBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(Config.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(key, value).commit();
    }
}
