package com.example.sweater.controllers;

import com.example.sweater.domains.User;
import com.example.sweater.services.ProfileService;
import com.example.sweater.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/profile")
    public String show(@AuthenticationPrincipal User user, Model model) {

        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile/index";
    }

    @PostMapping("/profile")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam String password,
            @RequestParam String email
    ) {

        profileService.update(user, password, email);

        return "redirect:/profile";
    }
}