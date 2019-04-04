package com.example.sweater.controllers;

import com.example.sweater.domains.Message;
import com.example.sweater.domains.User;
import com.example.sweater.repositories.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesController {

    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String list(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages = (filter != null && !filter.isEmpty()) ? messageRepository.findByTag(filter) : messageRepository.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "messages";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag
    ) {
        Message message = new Message(text, tag, user);
        messageRepository.save(message);

        return "redirect:/messages/";
    }
}