package com.example.morracineseadvanced;

import com.example.morracineseadvanced.data.model.UserModel;
import com.google.firebase.firestore.auth.User;

import java.util.List;

public interface OnUsersFetchedListener {
    void onUsersFetched(UserModel user);
}
