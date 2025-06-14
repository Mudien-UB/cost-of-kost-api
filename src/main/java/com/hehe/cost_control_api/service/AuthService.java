package com.hehe.cost_control_api.service;

import jakarta.validation.constraints.NotNull;

public interface AuthService {

    String login(@NotNull String username,@NotNull String password);

}
