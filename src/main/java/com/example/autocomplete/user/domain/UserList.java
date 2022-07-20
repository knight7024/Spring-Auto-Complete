package com.example.autocomplete.user.domain;

import com.example.autocomplete.global.domain.BaseDomain;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserList extends BaseDomain {
    private ArrayList<UserSignUp> userList;
}
