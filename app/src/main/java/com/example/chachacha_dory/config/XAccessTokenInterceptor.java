package com.example.chachacha_dory.config;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.chachacha_dory.config.ApplicationClass.X_ACCESS_TOKEN;
import static com.example.chachacha_dory.config.ApplicationClass.sSharedPreferences;

public class XAccessTokenInterceptor implements Interceptor {

    @Override
    @NonNull
    public Response intercept(@NonNull final Interceptor.Chain chain) throws IOException {
        final Request.Builder builder = chain.request().newBuilder();
        final String jwtToken = sSharedPreferences.getString(X_ACCESS_TOKEN, null);
        if (jwtToken != null) {
            builder.addHeader("x-access-token", jwtToken);
        }
//        final String jwtToken = X_ACCESS_TOKEN;
//        builder.addHeader("x-access-token", jwtToken);

        return chain.proceed(builder.build());
    }
}