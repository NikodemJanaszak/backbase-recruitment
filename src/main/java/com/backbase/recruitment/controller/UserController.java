package com.backbase.recruitment.controller;

import com.backbase.recruitment.model.user.User;
import com.backbase.recruitment.service.UnauthenticatedUserException;
import com.backbase.recruitment.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class UserController {

    private final UserService userService;

    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) throws UnauthenticatedUserException {
        User user = userService.authenticateUser(username, password);
        return userService.generateToken(user);
    }
}