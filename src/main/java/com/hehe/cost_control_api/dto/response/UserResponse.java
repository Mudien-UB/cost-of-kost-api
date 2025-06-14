package com.hehe.cost_control_api.dto.response;

import com.hehe.cost_control_api.model.Users;

public record UserResponse(
        String userId,
        String fullName,
        String username,
        String email
) {

    public static UserResponse of(Users user) {
        return new UserResponse(
                user.getId().toString(),
                user.getFullName(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
