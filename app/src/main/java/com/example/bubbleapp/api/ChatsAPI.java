package com.example.bubbleapp.api;

import androidx.annotation.NonNull;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.R;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
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
    public JSONArray getContacts(String token){
        final JSONArray[] res = new JSONArray[1];
        Call<ResponseBody> call = webServiceAPI.getContacts("Bearer "+token);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
        try {
            res[0] = new JSONArray(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res[0];
    }
    public JSONObject login(String name, String password) {
        final JSONObject[] res = new JSONObject[1];
        MultipartBody loginBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("password", password)
                .build();
        Call<ResponseBody> call = webServiceAPI.login(loginBody);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
        try {
            res[0] = new JSONObject(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res[0];
    }

    public JSONObject register(String fullName, String nickName, String password) {
        final JSONObject[] res = new JSONObject[1];
        MultipartBody registerBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", fullName)
                .addFormDataPart("nickName", nickName)
                .addFormDataPart("password", password)
                .build();
        Call<ResponseBody> call = webServiceAPI.register(registerBody);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
        try {
            res[0] = new JSONObject(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res[0];
    }

}


