package com.example.movies.data.remote;

import androidx.annotation.NonNull;

import com.example.movies.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private static final String API_TOKEN = BuildConfig.POISKINO_API_KEY;
    private static final String HEADER_NAME="X-API-KEY";
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request=chain.request();
        Request newRequest=request.newBuilder()
                .addHeader(HEADER_NAME,API_TOKEN)
                .build();

        return chain.proceed(newRequest);
    }
}
