package com.example.sweater.controllers;

import com.example.sweater.domains.Message;
import com.example.sweater.domains.User;
import com.example.sweater.repositories.MessageRepository;
import com.example.sweater.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/messages")
public class MessagesController {

    @Autowired
    private MessageRepository messageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String list(@RequestParam(required = false) String filter, Model model) {
        Iterable<Message> messages = (filter != null && !filter.isEmpty()) ? messageRepository.findByTag(filter) : messageRepository.findAll();

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "messages/index";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        message.setUser(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String filename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + filename));

                message.setFilename(filename);
            }

            messageRepository.save(message);
        }

        Iterable<Message> messages =  messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "messages/index";
    }
}