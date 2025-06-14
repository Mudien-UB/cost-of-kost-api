package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Users;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    Users createUser(@NotNull String email, @NotNull String username, @NotNull String password, String fullName);

    Users getFromContext();

    boolean isUsernameAlreadyExist(@NotNull String username);
    boolean isEmailAlreadyExist(@NotNull String email);
}
