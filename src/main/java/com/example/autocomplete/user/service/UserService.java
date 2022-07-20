package com.example.autocomplete.user.service;

import com.example.autocomplete.global.domain.BaseResponse;
import com.example.autocomplete.user.domain.UserSignUp;

public interface UserService {
    void signUp(UserSignUp userSignUp);

    BaseResponse autoCompleteUserName(String name);

    Long countUsers();
}
