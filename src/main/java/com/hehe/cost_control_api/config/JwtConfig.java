package com.hehe.cost_control_api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtConfig {

    @Value("${jwt.secret_key:your-very-secure-random-key-here-123456}")
    private String secretKey;

    @Value("${jwt.issuer:is_you}")
    private String issuer;

}

