package com.example.sweater.controllers;

import com.example.sweater.domains.User;
import com.example.sweater.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @GetMapping("/registration")
    public String index(Map<String, Object> model) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Map<String, Object> model) {

        if (!userService.create(user)) {
            model.put("message", "User exists!");
            return "auth/registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code, Model model) {

        boolean isActivated = userService.activate(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code is not found!");
        }

        return "redirect:/login";
    }
}