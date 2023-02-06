package com.project.controller;

import com.project.dto.request.UserAccountRequest;
import com.project.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService userAccountService;

    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("user", new UserAccountRequest());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(UserAccountRequest userAccountRequest) {
        userAccountService.createUser(userAccountRequest.toEntity());
        return "redirect:/home";
    }


}
