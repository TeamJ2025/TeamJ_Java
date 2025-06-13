package com.example.message.controller;

import com.example.message.service.MessageService;
import com.example.message.model.Message;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MessageController {
    private final MessageService service;

    public MessageController(MessageService service){
        this.service =service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        List<Message> messages = service.getAllMessages();
        model.addAttribute("messages", messages);
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           Model model) {
        if(username.isBlank() || password.isBlank()){
            model.addAttribute("error", "両方入力してください");
        } else {
            service.addMessage(username, password);
        }
        List<Message> messages = service.getAllMessages();
        model.addAttribute("messages", messages);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    @GetMapping("/sales")
    public String salesPage() {
        return "sales";
    }

    @GetMapping("/demand")
    public String demandPage() {
        return "demand";
    }

    @GetMapping("/forecast")
    public String forecastPage() {
        return "forecast";
    }

    @GetMapping("/brands")
    public String brandsPage() {
        return "brands";
    }

    @GetMapping("/staff")
    public String staffPage() {
        return "staff";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {

        List<Message> users = service.getAllMessages();
        boolean found = false;

        for (Message user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                found = true;
                break;
            }
        }


        if (found) {
            model.addAttribute("ok", "いかしてるぜ");
            return "main";
        } else {
            return "notwelcome";
        }

        // if(username.equals(correctUsername) && password.equals(correctPassword)){
        //     model.addAttribute("ok", "いかしてるぜ");
        //     return "welcome";
        // } else {
        //     return "notwelcome";
        // }
    }
}
