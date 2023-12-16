package com.example.demo.controller;

import com.example.demo.dto.UserSignupDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserSignupDto dto) {
        return userService.signup(dto);
    }
}
