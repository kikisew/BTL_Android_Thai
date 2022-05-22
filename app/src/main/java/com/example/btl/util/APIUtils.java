package com.example.btl.util;

import com.example.btl.retrofit.APIServiceInterface;
import com.example.btl.retrofit.RetrofitInstance;

import retrofit2.Retrofit;

public class APIUtils {
    public static APIServiceInterface getApiServiceInterface(){
        Retrofit retrofitInstance = RetrofitInstance.getRetrofitInstance();
        return retrofitInstance.create(APIServiceInterface.class);
    }
}