package com.example.morracineseadvanced;

import com.example.morracineseadvanced.data.model.UserModel;

import java.util.List;

public interface OnUsersFetchedListener {
    void onUsersFetched(List<UserModel> users);
}
