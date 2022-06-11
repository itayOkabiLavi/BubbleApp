package com.example.bubbleapp.api;

import com.example.bubbleapp.payloads.Login;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WebServiceAPI {
    @POST("Users/login")
    Call<String> login(@Body MultipartBody login);
/*
    public static String Login(String name, String password) throws IOException {
        URL url = new URL("https://localhost:7135/api/users/login");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        //byte[] response = new byte[1000];
        StringBuilder response = null;
        try {
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            String payload = String.format("{\"name\":%s, \"password\":%s", name, password);
            byte[] payloadBytes = payload.getBytes(StandardCharsets.UTF_8);
            OutputStream out = urlConnection.getOutputStream();
            out.write(payloadBytes);

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), StandardCharsets.UTF_8));
            response = new StringBuilder();
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        System.out.println(response);
        return response.toString();
    }
*/
}
