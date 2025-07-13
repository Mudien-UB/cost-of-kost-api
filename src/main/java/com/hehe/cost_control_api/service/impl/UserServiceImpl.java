package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.Users;
import com.hehe.cost_control_api.repository.UserRepository;
import com.hehe.cost_control_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users createUser(String email, String username, String password, String fullName) {

        Users user = Users.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .fullName(fullName)
                .email(email)
                .build();

        return userRepository.save(user);
    }

    @Override
    public Users getFromContext() {
        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("You are not logged in"));
    }

    @Override
    public boolean isUsernameAlreadyExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isEmailAlreadyExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Users updateUser(Users users, String email, String username, String fullName) {

        if(email != null) {
            users.setEmail(email);
        }
        if(username != null) {
            users.setUsername(username);
        }
        if(fullName != null) {
            users.setFullName(fullName);
        }
        return userRepository.save(users);
    }

    @Override
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Users getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Users getById(String id) {
        return userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
