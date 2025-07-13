package com.hehe.cost_control_api.dto.request;

import com.hehe.cost_control_api.dto.validation_group.OnLogin;
import com.hehe.cost_control_api.dto.validation_group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class AuthRequest {

    @NotBlank(message = "username cannot be blank", groups = {OnLogin.class, OnUpdate.class})
    private String usernameOrEmail;

    @NotBlank(message = "password cannot be blank", groups = {OnLogin.class, OnUpdate.class})
    @Length(max = 8, message = "max length is 8", groups = {OnLogin.class, OnUpdate.class})
    private String password;

}
