package com.example.morracineseadvanced.data;

import com.example.morracineseadvanced.model.AuthManager;

public class LoginRepository {

    private AuthManager authManager;

    public LoginRepository(AuthManager authManager) {
        this.authManager = authManager;
    }

    public static LoginRepository getInstance(LoginDataSource loginDataSource) {
        return null;
    }

    public boolean login(String username, String password) {
        return authManager.login(username, password);
    }

    public boolean register(String username, String password) {
        return authManager.register(username, password);
    }
}
