package com.example.retrofit_example.retrof;

import com.example.retrofit_example.response.ArticleData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("anime?page[limit]=1&page[offset]=1")
    Call<ArticleData> getAnimeArticles();

    @GET("manga?page[limit]=1&page[offset]=1")
    Call<ArticleData> getMangaArticles();
}
