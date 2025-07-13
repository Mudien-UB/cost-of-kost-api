package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.UserRequest;
import com.hehe.cost_control_api.dto.response.UserResponse;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        Users user = userService.getFromContext();
        return BaseResponseUtil.buildResponse(HttpStatus.OK, "me", UserResponse.of(user));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest) {

        Users user = userService.getFromContext();

        Users updatedUser = userService.updateUser(
                user,
                userRequest.getEmail(),
                userRequest.getUsername(),
                userRequest.getFullName()
        );
        return BaseResponseUtil.buildResponse(HttpStatus.OK, "update", UserResponse.of(updatedUser));

    }


}
