package com.backbase.recruitment.service;

import com.backbase.recruitment.model.user.User;
import com.backbase.recruitment.model.user.UsersConst;
import com.backbase.recruitment.security.TokenGenerator;
import com.backbase.recruitment.service.exception.UnauthenticatedUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final TokenGenerator tokenGenerator;

    public User authenticateUser(String username, String password) throws UnauthenticatedUserException {
        User user = findByUsername(username);
        if (password.equals(user.getPassword())) {
            return user;
        } else {
            throw new UnauthenticatedUserException("Password doesn't match given username");
        }
    }

    public User findByUsername(String username) throws UnauthenticatedUserException {
        return UsersConst.getUsers().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UnauthenticatedUserException("Couldn't find user with given name"));
    }

    public String generateToken(User user) {
        return tokenGenerator.generateToken(user);
    }
}