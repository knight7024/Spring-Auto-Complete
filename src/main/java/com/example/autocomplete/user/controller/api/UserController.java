package com.example.autocomplete.user.controller.api;

import com.example.autocomplete.global.domain.BaseResponse;
import com.example.autocomplete.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/user")
@RestController("UserControllerWithAPI")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse> searchUser(@RequestParam("name") String name) {
        BaseResponse response = userService.autoCompleteUserName(name);
        return new ResponseEntity<>(response, response.getResponseStatus());
    }
}
