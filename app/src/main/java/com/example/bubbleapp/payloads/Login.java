package com.example.bubbleapp.payloads;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class Login {
    RequestBody requestBody;

    public Login(String name, String password) {
        this.requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", name)
                .addFormDataPart("password", password)
                .build();
    }
}
