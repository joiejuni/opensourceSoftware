package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class helloController {

    @GetMapping("/signup")
    public ModelAndView helloFunction() {
        return new ModelAndView("signup");
    }
}
