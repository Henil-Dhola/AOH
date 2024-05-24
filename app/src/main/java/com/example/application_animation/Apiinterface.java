package com.example.application_animation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {
    String BASE_URL="https://newsapi.org/v2/";

    @GET("top-headlines")
    Call<mainNews> getNews(
            @Query("country") String country,
            @Query("pageSize") int pageSize,
            @Query("apikey") String apikey
    );
    @GET("top-headlines")
    Call<mainNews> getCategoryNews(
            @Query("country") String country,
            @Query("category") String category,
            @Query("pageSize") int pageSize,
            @Query("apikey") String apikey
    );
    @GET("everything")
    Call<mainNews> getNewsSearch(
            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apikey") String apikey
    );
}
