package com.example.btl.retrofit;



import com.example.btl.model.Article;
import com.example.btl.model.Category;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIServiceInterface {

    @GET("category")
    Call<List<Category>> getAllCategories();

    @GET("article/categoryId")
    Call<List<Article>> getArticleBySlug(@Query("id") String id);

    @GET("articles")
    Call<List<Article>> getAllArticles();

    @GET("article/id/{id}")
    Call<Article> getArticleById(@Path("id") String id);

    @GET("article/search")
    Call<List<Article>> getArticlesByKey(@Query("title") String key);



}
