package com.six.carrental.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Lian
 * @create 2018/7/29
 * @Describe SharedPreferences工具类
 */
public class SharedPreferencesUtil {
    private static final String FILE_NAME = "User";
    private static SharedPreferences sSharedPreferences;
    private static SharedPreferences.Editor sEditor;
    private static SharedPreferencesUtil mInstance;

    private SharedPreferencesUtil(Context context) {
        sSharedPreferences = context.getSharedPreferences ( FILE_NAME, Context.MODE_PRIVATE );
        sEditor = sSharedPreferences.edit ();
    }

    public static SharedPreferencesUtil getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SharedPreferencesUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharedPreferencesUtil ( context );
                }
            }
        }
        return mInstance;
    }

    /*保存数据*/
    public void setDate(String key, String value) {
        sEditor.putString ( key, value );
        sEditor.commit ();
    }

    /*取出数据*/
    public String getData(String key) {
        return sSharedPreferences.getString ( key, "null" );
    }


    public void setTime(String key, long value) {
        sEditor.putLong ( key, value );
        sEditor.commit ();
    }

}
