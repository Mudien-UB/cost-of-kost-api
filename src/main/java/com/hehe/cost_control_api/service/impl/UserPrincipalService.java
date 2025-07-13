package com.hehe.cost_control_api.service.impl;

import com.hehe.cost_control_api.model.UserPrincipal;
import com.hehe.cost_control_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class UserPrincipalService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return new UserPrincipal(userRepository.findByEmailOrUsername(usernameOrEmail,usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException(usernameOrEmail)));
    }
}
