package com.payworks.api;

import android.content.Context;
import com.payworks.utils.LogUtils;
import com.payworks.utils.PrefUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;


public class Client {

    public static final String TAG = LogUtils.makeLogTag(Client.class);

    private static volatile OkHttpClient mClient;


    private Client() {
        mClient = new OkHttpClient();
    }

    public static OkHttpClient getClientInstance(final Context context) {
        final int cacheSize = 30 * 1024 * 1024; //30MB


        //creating request Interceptor
        Interceptor requestInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request newRequest;
                newRequest = original.newBuilder()
                        .header("Accept", "application/json")
                        .header("Authorization", "Token token=" + PrefUtils.getAuthToken(context))
                        .build();
                return chain.proceed(newRequest);
            }
        };

        //creating log Interceptor
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (mClient == null) {
            synchronized (Client.class) {
                if (mClient == null) {
                    mClient = new OkHttpClient().newBuilder()
                            .cache(new Cache(context.getApplicationContext().getCacheDir(), cacheSize))
                            .readTimeout(60, TimeUnit.SECONDS)
                            .connectTimeout(60, TimeUnit.SECONDS)
                            .addInterceptor(requestInterceptor)
                            .addInterceptor(logging)
                            .build();


                }
            }
        }
        return mClient;
    }


}
