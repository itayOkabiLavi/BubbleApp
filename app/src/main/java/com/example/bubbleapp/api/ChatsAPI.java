package com.example.bubbleapp.api;

import androidx.annotation.NonNull;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.R;
import com.example.bubbleapp.payloads.Login;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChatsAPI {

    Retrofit retrofit;

    WebServiceAPI webServiceAPI;


    public ChatsAPI() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public void login(String name, String password) {
        Call<String> call = webServiceAPI.login(/*new Login(name,password)*/new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("password", password)
                .build());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                String body = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });

    }

}


