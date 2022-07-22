package com.example.suryatradersadmin.api;

import android.util.Log;

import com.example.suryatradersadmin.tokenmanager.TokenManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitClient {


    private static final String BASE_URL="http://192.168.1.44:8080/All-Projects/MarkLift/Surya-Backend/surya/public/";
    private static RetroFitClient mInstance;
    private static final String TAG="Retrofit";
    private Retrofit retrofit;

    private RetroFitClient ()
    {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                Request request = chain.request();
                if (TokenManager.mInstance== null)
                {
                    TokenManager.getInstance();
                    return chain.proceed(request);
                } else
                {
                    String s=TokenManager.getInstance().getString("KEY_JWT_TOKEN","abc").trim();
                    Log.d(TAG,s);
                    return chain.proceed(request.newBuilder().
                            addHeader("Authorization","Bearer "+TokenManager.getInstance().getString("KEY_JWT_TOKEN","abc").trim()).build());
                }

            }

        }).build();
        retrofit=new Retrofit.Builder().client(okHttpClient).
                baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized RetroFitClient getInstance()
    {
        if (mInstance==null){

            mInstance=new RetroFitClient();
        }
        return mInstance;
    }

    public Api getApi(){

        return retrofit.create(Api.class);
    }
}
