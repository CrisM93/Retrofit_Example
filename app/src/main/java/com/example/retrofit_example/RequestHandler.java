package com.example.retrofit_example;

import android.content.Context;
import android.util.Log;

import com.example.retrofit_example.response.ArticleData;
import com.example.retrofit_example.retrof.ApiService;
import com.example.retrofit_example.retrof.apiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestHandler {
    private apiClient apiclient = apiClient.getInstance();
    private ApiService apiService = apiclient.getApiService();

    private RequestListener requestListener;

    private Context context;

    public RequestHandler(Context context, RequestListener requestListener) {
        this.context = context;
        this.requestListener = requestListener;

    }

    public void getArticles(StringUtlis.ARTICLES articlesType) {
        Call<ArticleData> call = apiService.getAnimeArticles();
        if (articlesType == StringUtlis.ARTICLES.ANIME) {
            call = apiService.getAnimeArticles();

        } else if (articlesType == StringUtlis.ARTICLES.MANGA) {
            call = apiService.getMangaArticles();
        }
        call.enqueue(new Callback<ArticleData>() {
            @Override
            public void onResponse(Call<ArticleData> call, Response<ArticleData> response) {
                if (response.isSuccessful() && response.code() == 200) {
                    if (articlesType == StringUtlis.ARTICLES.ANIME) {
                        requestListener.animeResponse(response.body());
                    } else if (articlesType == StringUtlis.ARTICLES.MANGA) {
                        requestListener.mangaResponse(response.body());
                    }
                } else {
                    Log.e("retrofitresponse", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<ArticleData> call, Throwable t) {
                Log.e("retrofitresponse", t.getMessage());


            }
        });
    }

    public interface RequestListener {
        void animeResponse(ArticleData articleData);

        void mangaResponse(ArticleData articleData);

    }
}
