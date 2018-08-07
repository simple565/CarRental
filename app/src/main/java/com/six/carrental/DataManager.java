package com.six.carrental;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DataManager {
    private static DataManager mInstance;
    private Retrofit mRetrofit;
    // File cacheFile = new File( BaseApplication.getmContext().getCacheDir(), "cache");
    //Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
    ApiService mApiService;

    public static DataManager getInstance() {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                if (mInstance == null) {
                    mInstance = new DataManager ();
                }
            }
        }
        return mInstance;
    }

    private DataManager() {
        mRetrofit = new Retrofit.Builder ()
                .baseUrl ( ApiService.BASE_URL )
                .addConverterFactory ( ScalarsConverterFactory.create () )
                .addConverterFactory ( GsonConverterFactory.create () )
                .addCallAdapterFactory ( RxJava2CallAdapterFactory.create () )
                .client ( getOkHttpClient () )
                .build ();
        mApiService = mRetrofit.create ( ApiService.class );

    }

    public ApiService getApiService() {
        return mApiService;
    }

    private OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor ( new HttpLoggingInterceptor.Logger () {
            @Override
            public void log(String message) {
                Log.d ( "数据接收", "OkHttp====Message:" + message );
            }
        } );
        loggingInterceptor.setLevel ( level );

        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder ();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor ( loggingInterceptor );
        return httpClientBuilder.build ();
    }

}
