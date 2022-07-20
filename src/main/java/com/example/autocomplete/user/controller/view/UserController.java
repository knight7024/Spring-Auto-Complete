package com.example.autocomplete.user.controller.view;

import com.example.autocomplete.user.domain.UserSignUp;
import com.example.autocomplete.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("UserControllerWithModel")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String signUpGet(@ModelAttribute("userSignUp") UserSignUp userSignUp) {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpPost(@Validated UserSignUp userSignUp, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }
        userService.signUp(userSignUp);
        return "redirect:/";
    }

    @GetMapping("/sign-in")
    public String signInGet() {
        return "sign-in";
    }
}
