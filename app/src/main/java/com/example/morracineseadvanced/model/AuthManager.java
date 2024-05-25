package com.example.morracineseadvanced.model;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AuthManager {
    private String serverUrl;
    private OkHttpClient client;

    public AuthManager(String serverUrl) {
        this.serverUrl = serverUrl;
        this.client = new OkHttpClient();
    }

    public boolean login(String username, String password) {
        String url = serverUrl + "/verifyCredentials?username=" + username + "&password=" + password;
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(null, new byte[0])) // Required for POST request, but with no body content
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Boolean.parseBoolean(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
