package com.hehe.cost_control_api.service;

import com.hehe.cost_control_api.model.Users;
import jakarta.validation.constraints.NotNull;

public interface UserService {

    Users createUser(@NotNull String email, @NotNull String username, @NotNull String password, String fullName);

    Users getFromContext();

    Users getByEmail(String email);
    Users getByUsername(String username);
    Users getById(String id);

    boolean isUsernameAlreadyExist(@NotNull String username);
    boolean isEmailAlreadyExist(@NotNull String email);

    Users updateUser(@NotNull Users users, String email, String username, String fullName);

}
