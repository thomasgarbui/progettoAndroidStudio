package com.example.morracineseadvanced.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AuthManager {

    private String serverUrl;

    public AuthManager(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public boolean login(String username, String password) {
        try {
            String requestBody = "username=" + username + "&password=" + password;
            String response = sendHttpRequest(serverUrl + "/login", "POST", requestBody);

            return "success".equals(response); // Modifica questa condizione in base alla risposta del tuo server
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean register(String username, String password) {
        try {
            // Costruzione della richiesta HTTP per la registrazione
            String requestBody = "username=" + username + "&password=" + password;
            String response = sendHttpRequest(serverUrl + "/register", "POST", requestBody);

            // Analisi della risposta del server e restituzione del risultato
            return "success".equals(response); // Modifica questa condizione in base alla risposta del tuo server
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private String sendHttpRequest(String urlString, String method, String requestBody) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);

        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }

        return response.toString();
    }

    public boolean Register(String string, String string1) {
        return false;
    }
}
