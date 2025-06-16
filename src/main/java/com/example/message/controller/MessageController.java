package com.example.message.controller;

import com.example.message.service.MessageService;
import com.example.message.entity.SalesData;
import com.example.message.model.Message;
import com.example.message.service.SalesDataService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


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
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        if(username.isBlank() || email.isBlank()|| password.isBlank()){
            model.addAttribute("error", "両方入力してください");
        } else {
            service.addMessage(username, email, password);
        }
        List<Message> messages = service.getAllMessages();
        model.addAttribute("messages", messages);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
    // @GetMapping("/sales")
    // public String salesPage(Model model) {
    //     List<SalesData> salesList = service.getAllSalesData();
    //     model.addAttribute("salesList", salesList);
    //     return "sales";
    // }

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
    @GetMapping("/staff_change")
    public String staff_changePage() {
        return "staff_change";
    }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/sales_input")
    public String sales_inputPage() {
        return "sales_input";
    }

    @GetMapping("/sales_change")
    public String sales_changePage() {
        return "sales_change";
    }

@PostMapping("/login")
public String login(@RequestParam String email,
                    @RequestParam String password,
                    Model model) {

    List<Message> users = service.getAllMessages();
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    boolean found = false;

    for (Message user : users) {
        if (user.getEmail().equals(email)
                && passwordEncoder.matches(password, user.getPassword())) {
            found = true;
            break;
        }
    }

    if (found) {
        model.addAttribute("ok", "いかしてるぜ");
        return "main";
    } else {
        model.addAttribute("error", "メールアドレスまたはパスワードが違います");
        return "notwelcome";
    }
}
}
