package com.example.bubbleapp.api;

import static java.nio.file.Files.probeContentType;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.R;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
                .baseUrl("http://"+MyApplication.server+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
    }

    public JSONArray getContacts(String token) {
        final JSONArray[] res = new JSONArray[1];
        Call<ResponseBody> call = webServiceAPI.getContacts("Bearer " + token);
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

    public JSONArray getMessages(String token, String contactId) {
        final JSONArray[] res = new JSONArray[1];
        Call<ResponseBody> call = webServiceAPI.getMessages("Bearer " + token, contactId);
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

    public JSONObject login(String name, String password, String androidToken) {
        final JSONObject[] res = new JSONObject[1];
        MultipartBody loginBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("password", password)
                .addFormDataPart("androidToken", androidToken)
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public JSONObject register(String fullName, String nickName, String password, String androidToken, File profileImg) throws IOException {
        final JSONObject[] res = new JSONObject[1];
        MultipartBody registerBody;
        if (profileImg != null) {
            registerBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("name", fullName)
                    .addFormDataPart("nickName", nickName)
                    .addFormDataPart("password", password)
                    .addFormDataPart("androidToken", androidToken)
                    .addFormDataPart("profileImage", profileImg.getName(), RequestBody.create(MediaType.get("image/jpeg"), profileImg))
                    .build();
        } else {
            registerBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("name", fullName)
                    .addFormDataPart("nickName", nickName)
                    .addFormDataPart("password", password)
                    .addFormDataPart("androidToken", androidToken)
                    .build();
        }
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

    public void sendMessage(Message message) {
        MultipartBody sendMessageBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", message.toId)
                .addFormDataPart("content", message.content)
                //.addFormDataPart("formFile", "null")
                .build();
        Call<ResponseBody> call = webServiceAPI.sendMessage("Bearer " + MyApplication.token, message.toId, sendMessageBody);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
    }

    public JSONObject getUser(String token) {
        final JSONObject[] res = new JSONObject[1];
        Call<ResponseBody> call = webServiceAPI.getUser("Bearer " + token);
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


    public void addContact(Chat chat) {
        MultipartBody addContactBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("id", chat.contactName)
                .addFormDataPart("name", chat.contactName)
                .addFormDataPart("server", chat.server)
                .build();
        Call<ResponseBody> call = webServiceAPI.addContact("Bearer " + MyApplication.token, addContactBody);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
    }
}


