package com.example.bubbleapp.api;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.bubbleapp.MyApplication;
import com.example.bubbleapp.R;
import com.example.bubbleapp.database.MyDao;
import com.example.bubbleapp.models.Chat;
import com.example.bubbleapp.models.Message;
import com.example.bubbleapp.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.util.List;
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
    private MutableLiveData messagesListData;
    private MyDao myDao;
    Retrofit retrofit;

    WebServiceAPI webServiceAPI;


    public ChatsAPI(MutableLiveData messagesListData, MyDao dao) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        this.messagesListData = messagesListData;
        this.myDao = dao;
    }

    public ChatsAPI() {
        this(new MutableLiveData<>(), null);
    }

    public void getContacts() {
        final JSONArray[] res = new JSONArray[1];
        Call<List<User>> call = webServiceAPI.getContacts("Bearer " + MyApplication.token);
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                new Thread(() -> {
                    //myDao.clear();
                    assert response.body() != null;
                    for (User user : response.body()) {
                        Chat chat = new Chat(user.name, user.server, "");
                        myDao.insertChats(chat);
                    }
                    messagesListData.postValue(myDao.getAllContacts());
                }).start();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
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
        return res[0];*/
    }

    public void getMessages(String contactId) {
        Call<List<Message>> call = webServiceAPI.getMessages("Bearer " + MyApplication.token, contactId);
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> result = executorService.submit(() -> {
            Response<ResponseBody> response = call.execute();
            assert response.body() != null;
            return response.body().string();
        });
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }*/
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                new Thread(() -> {
                    //myDao.clear();
                    assert response.body() != null;
                    for (Message message : response.body()) {
                        myDao.insertMessages(message);
                    }
                    messagesListData.postValue(myDao.getAllMessages(contactId));
                }).start();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    public void login(String name, String password, String androidToken) {
        MultipartBody loginBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("password", password)
                .addFormDataPart("androidToken", androidToken)
                .build();
        Call<ResponseBody> call = webServiceAPI.login(loginBody);
        SharedPreferences userDetails = MyApplication.context.getSharedPreferences("userdata", 0);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseString = null;
                try {
                    assert response.body() != null;
                    responseString = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseString);
                    SharedPreferences.Editor edit = userDetails.edit();
                    edit.putString("token", jsonObject.getString("token"));
                    edit.putString("user", jsonObject.getJSONObject("user").toString());
                    edit.apply();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
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
        return res[0];*/
    }

    public JSONObject register(String fullName, String nickName, String password, String androidToken) {
        final JSONObject[] res = new JSONObject[1];
        MultipartBody registerBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", fullName)
                .addFormDataPart("nickName", nickName)
                .addFormDataPart("password", password)
                .addFormDataPart("androidToken", androidToken)
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

    public JSONArray getUser(String token) {
        final JSONArray[] res = new JSONArray[1];
        Call<ResponseBody> call = webServiceAPI.getUser("Bearer " + token);
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


