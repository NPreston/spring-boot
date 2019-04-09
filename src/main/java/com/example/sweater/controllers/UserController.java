package com.example.sweater.controllers;

import com.example.sweater.domains.Role;
import com.example.sweater.domains.User;
import com.example.sweater.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "users/index";
    }

    @GetMapping("{user}")
    public String show(@PathVariable User user, Model model) {

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "users/show";
    }

    @PostMapping("{user}")
    public String store(
            @PathVariable User user,
            @RequestParam String username,
            @RequestParam Map<String, String> form
    ) {

        user.setUsername(username);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepository.save(user);

        return "redirect:/users";
    }
}