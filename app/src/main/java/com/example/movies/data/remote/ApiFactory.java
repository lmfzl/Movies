package com.example.movies.data.remote;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static final String BASE_URL="https://api.poiskkino.dev/v1.5/";

    private static final OkHttpClient client= new OkHttpClient.Builder()
            .addInterceptor(new AuthInterceptor())
            .build();

    private static final Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build();
    public static final ApiService API_SERVICE = retrofit.create(ApiService.class);
}
