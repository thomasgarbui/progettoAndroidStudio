package com.example.morracineseadvanced.model;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AuthManager {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private OkHttpClient client;
    private String serverUrl;

    public AuthManager(String serverUrl) {
        this.serverUrl = serverUrl;
        this.client = new OkHttpClient();
    }

    public boolean login(String username, String password) {
        try {
            String json = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
            String response = post(serverUrl + "/login", json);
            return "success".equals(response); // Modifica questa condizione in base alla risposta del tuo server
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            String json = "{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}";
            String response = post(serverUrl + "/register", json);
            return "success".equals(response);
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
