package com.example.sweater.controllers;

import com.example.sweater.domains.Message;
import com.example.sweater.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/")
    public String list(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();

        model.put("messages", messages);
        return "messages";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.put("messages", messages);

        return "messages";
    }

    @PostMapping("/add")
    public String add(@RequestParam String text, @RequestParam String tag) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        return "redirect:/messages/";
    }
}