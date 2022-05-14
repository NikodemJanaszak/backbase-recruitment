package com.backbase.recruitment.model.user;

import java.util.List;

public class UsersConst {

    private static final List<User> users = List.of(new User("user", "pass"));

    public static List<User> getUsers() {
        return users;
    }
}