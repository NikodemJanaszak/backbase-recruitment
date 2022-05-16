package com.backbase.recruitment.service;

import com.backbase.recruitment.model.user.User;
import com.backbase.recruitment.security.TokenGenerator;
import com.backbase.recruitment.service.exception.UnauthenticatedUserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private final UserService userService = new UserService(new TokenGenerator());

    @Test
    public void shouldAuthenticateUserForCorrectPassword() throws UnauthenticatedUserException {
        String username = "user";
        String password = "pass";

        User user = userService.authenticateUser(username, password);

        assertEquals(user.getUsername(), username);
        assertEquals(user.getPassword(), password);
    }

    @Test
    public void shouldNotAuthenticateUserForWrongPassword() {
        String username = "user";
        String password = "bad_password";

        assertThrows(UnauthenticatedUserException.class, () -> userService.authenticateUser(username, password));
    }

    @Test
    public void shouldThrowExceptionForNonExistingUsername() {
        String nonExistingUsername = "nonExisting";

        assertThrows(UnauthenticatedUserException.class, () -> userService.findByUsername(nonExistingUsername));
    }
}