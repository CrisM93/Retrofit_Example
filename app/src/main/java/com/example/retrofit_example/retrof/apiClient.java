package com.example.retrofit_example.retrof;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apiClient {
    private static apiClient instance = null;
    private final Retrofit retrofitCLiente;
    private final ApiService apiService;

    private apiClient() {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder
                .addInterceptor(new ApiInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .callTimeout(30, TimeUnit.SECONDS);

        OkHttpClient okHttpClient = okHttpBuilder.build();

        retrofitCLiente = new Retrofit.Builder()
                .baseUrl("https://kitsu.io/api/edge/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiService = retrofitCLiente.create(ApiService.class);
    }

    public static apiClient getInstance() {
        if (instance == null) {
            instance = new apiClient();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }


}
