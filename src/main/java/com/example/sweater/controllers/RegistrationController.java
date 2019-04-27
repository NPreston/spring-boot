package com.example.sweater.controllers;

import com.example.sweater.domains.Role;
import com.example.sweater.domains.User;
import com.example.sweater.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String index(Map<String, Object> model) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String registration(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "auth/registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }
}