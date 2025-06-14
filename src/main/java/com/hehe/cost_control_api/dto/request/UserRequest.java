package com.hehe.cost_control_api.dto.request;

import com.hehe.cost_control_api.dto.validation_group.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "cannot blank", groups = {OnCreate.class})
    private String username;

    @NotBlank(message = "cannot blank", groups = {OnCreate.class})
    private String password;

    @NotBlank(message = "cannot blank", groups = {OnCreate.class})
    private String email;

    @NotBlank(message = "cannot blank",  groups = {OnCreate.class})
    private String fullName;

}
