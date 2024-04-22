package com.example.morracineseadvanced.data;

import com.example.morracineseadvanced.data.model.LoggedInUser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {

            if (username.equals("admin") && password.equals("adminadmin")) {
                return new Result.Success<>(new LoggedInUser("0", "Porseo"));
            } else {
                return new Result.Error(new IOException("Invalid Credentials", new Exception()));
            }
        }catch(Exception e){
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}