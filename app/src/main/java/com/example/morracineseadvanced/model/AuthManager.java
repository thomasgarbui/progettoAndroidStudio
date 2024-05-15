package com.example.morracineseadvanced.model;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthManager {
    private FirebaseAuth auth;

    public AuthManager(){
        auth = FirebaseAuth.getInstance();
    }
    public boolean Login(String email, String password){
        try{
            auth.signInWithEmailAndPassword(email,password);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public boolean Register(String email,String password){
        try{
            auth.createUserWithEmailAndPassword(email,password);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public boolean Logout(){
        try{
            auth.signOut();
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}
