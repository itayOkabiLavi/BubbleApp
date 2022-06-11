package com.example.bubbleapp.api;


import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServiceAPI {
    @POST("Users/login")
    Call<ResponseBody> login(@Body MultipartBody login);
    @POST("Users/register")
    Call<ResponseBody> register(@Body MultipartBody register);
}
