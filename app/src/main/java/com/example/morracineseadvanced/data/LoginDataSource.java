package com.example.morracineseadvanced.data;

import com.example.morracineseadvanced.data.model.LoggedInUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private FirebaseAuth auth;

    public LoginDataSource(){
        auth = FirebaseAuth.getInstance();
    }
    public Result<LoggedInUser> login(String email, String password) {

        try {
            auth.signInWithEmailAndPassword(email,password);
            String s1 = auth.getUid();
            FirebaseUser s = auth.getCurrentUser();
            if (email.equals("admin") && password.equals("adminadmin")) {
                return new Result.Success<>(new LoggedInUser("0", "TEST"));
            } else {
                return new Result.Error(new IOException("Invalid Credentials", new Exception()));
            }
        }catch(Exception e){
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        auth.signOut();
        // TODO: revoke authentication
    }
    public Result<LoggedInUser> register(String email,String password){
        try{
            auth.createUserWithEmailAndPassword(email,password);
            return new Result.Success<>(new LoggedInUser("0", "TEST"));
        }catch(Exception ex){
            return new Result.Error(new IOException("Error logging in", ex));
        }
    }
}