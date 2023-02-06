package com.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "forward:/home";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}