package com.example.demo.controller;

import com.example.demo.dto.UserSignupDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;  // 수정된 부분
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public Long signup(@RequestBody UserSignupDto dto) {
        return userService.signup(dto);
    }

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("userSignupDto", new UserSignupDto());
        return "signup";
    }
}
