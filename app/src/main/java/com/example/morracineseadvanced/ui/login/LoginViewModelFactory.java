package com.example.morracineseadvanced.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.morracineseadvanced.data.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final LoginRepository loginRepository;

    public LoginViewModelFactory(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(loginRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
