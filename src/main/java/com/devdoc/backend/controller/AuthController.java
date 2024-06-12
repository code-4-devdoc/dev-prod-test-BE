// AuthController.java

package com.devdoc.backend.controller;

import com.devdoc.backend.model.User;
import com.devdoc.backend.repository.UserRepository;
import com.devdoc.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인
    @PostMapping("/login")
    public boolean login(@RequestBody User loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return authentication.isAuthenticated();
    }

    // 회원가입
    @PostMapping("/api/register")
    public boolean register(@RequestBody User registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return false;
        }
        
        registerRequest.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userService.saveUser(registerRequest);

        return true;
    }
}
