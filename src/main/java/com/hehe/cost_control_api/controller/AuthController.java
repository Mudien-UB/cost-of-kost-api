package com.hehe.cost_control_api.controller;

import com.hehe.cost_control_api.dto.request.AuthRequest;
import com.hehe.cost_control_api.dto.request.UserRequest;
import com.hehe.cost_control_api.dto.response.AuthResponse;
import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.service.AuthService;
import com.hehe.cost_control_api.service.UserService;
import com.hehe.cost_control_api.util.BaseResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody AuthRequest authRequest) {

        String token = authService.login(authRequest.getUsername(), authRequest.getPassword());
        if(token == null) {
            return BaseResponseUtil.buildResponse(HttpStatus.UNAUTHORIZED, "invalid credentials", null);
        }

        return BaseResponseUtil.buildResponse(HttpStatus.OK, "login success", AuthResponse.of(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody UserRequest userRequest) {

        Users user = userService.createUser(
                userRequest.getEmail(),
                userRequest.getUsername(),
                userRequest.getPassword(),
                userRequest.getFullName()
        );

        if(user == null) {
            return  BaseResponseUtil.buildResponse(HttpStatus.BAD_REQUEST, "invalid request", null);
        }

        String token = authService.login(user.getUsername(), userRequest.getPassword());

        return BaseResponseUtil.buildResponse(HttpStatus.OK, "register success", AuthResponse.of(token));

    }


    @GetMapping("/is-email-used")
    public ResponseEntity<?> checkEmail(@RequestParam() String email) {
        boolean isUsed = userService.isEmailAlreadyExist(email);

        return BaseResponseUtil.buildResponse(HttpStatus.OK, isUsed ? "email is already registered" : "email is available" , isUsed);
    }

    @GetMapping("/is-username-used")
    public ResponseEntity<?> checkUsername(@RequestParam() String username) {
        boolean isUsed = userService.isUsernameAlreadyExist(username);

        return BaseResponseUtil.buildResponse(HttpStatus.OK, isUsed ? "username is already registered" : "username is available" , isUsed);
    }

}
