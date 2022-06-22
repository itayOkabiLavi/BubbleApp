package com.example.bubbleapp.api;


import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @POST("Users/login")
    Call<ResponseBody> login(@Body MultipartBody login);

    @POST("Users/register")
    Call<ResponseBody> register(@Body MultipartBody register);

    @GET("Contacts")
    Call<List<User>> getContacts(@Header("Authorization") String token);

    @GET("Contacts/{id}/messages")
    Call<List<Message>> getMessages(@Header("Authorization") String token, @Path("id") String contactId);

    @GET("Users/getUser")
    Call<ResponseBody> getUser(@Header("Authorization") String token);

    @POST("Contacts/{id}/messages")
    Call<ResponseBody> sendMessage(@Header("Authorization") String token, @Path("id") String id, @Body MultipartBody multipartBody);

    @POST("Contacts")
    Call<ResponseBody> addContact(@Header("Authorization") String token, @Body MultipartBody multipartBody);
}
