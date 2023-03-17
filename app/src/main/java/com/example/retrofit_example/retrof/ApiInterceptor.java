package com.example.retrofit_example.retrof;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiInterceptor implements Interceptor {
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        requestBuilder.addHeader("Accept", "application/vnd.api+json");
        requestBuilder.addHeader("Content-Type", "application/vnd.api+json");

        request=requestBuilder.build();
        return chain.proceed(request);

    }
}
