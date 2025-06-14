package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.response.UserResponse;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        Users user = userService.getFromContext();
        if(user == null) {
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED, "unauthorized request", null);
        }
        return BaseResponseUtil.buildResponse(HttpStatus.OK, "me", UserResponse.of(user));
    }


}
