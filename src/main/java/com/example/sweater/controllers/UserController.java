package com.example.sweater.controllers;

import com.example.sweater.domains.Role;
import com.example.sweater.domains.User;
import com.example.sweater.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String index(Model model) {

        model.addAttribute("users", userService.findAll());

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
            Model model,
            @PathVariable User user,
            @RequestParam String username,
            @RequestParam Map<String, String> form
    ) {

        boolean isUpdated = userService.update(user, username, form);

        if (isUpdated) {
            model.addAttribute("message", "User successfully updated");
        } else {
            model.addAttribute("message", "Update error!");
        }

        return "redirect:/users";
    }
}