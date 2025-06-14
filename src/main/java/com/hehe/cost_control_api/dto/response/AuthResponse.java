package com.hehe.cost_control_api.dto.response;

public record AuthResponse(
        String accessToken
) {
    public static AuthResponse of(String access_token) {
        return new AuthResponse(access_token);
    }
}
