package com.example.message.controller;

import com.example.message.service.MessageService;
import com.example.message.model.Message;
import com.example.message.repository.MessageRepository;
import com.example.message.service.SalesDataService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.message.model.ForecastResult;
import com.example.message.service.ForecastService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.message.model.CsvForecastRecord;
import com.example.message.service.CsvForecastService;
import com.example.message.entity.SalesData;
import java.util.List;

import com.example.message.model.ForecastResult;
import com.example.message.service.ForecastService;


@Controller
public class MessageController {
    private final MessageService service;
    private final MessageRepository repository;

    public MessageController(MessageService service, MessageRepository repository){
        this.service =service;
        this.repository = repository;
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
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam String password,
                           Model model) {
        if(name.isBlank() || email.isBlank()|| password.isBlank()){
            model.addAttribute("error", "両方入力してください");
            return "register"; 
        } else {
            service.addMessage(name, email, password);
        }
        List<Message> messages = service.getAllMessages();
        model.addAttribute("messages", messages);
        return "register";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    // @PostMapping("/login")
    // public String login(@RequestParam String email,
    //                     @RequestParam String password,
    //                     Model model) {

    //     List<Message> users = service.getAllMessages();
    //     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //     boolean found = false;

    //     for (Message user : users) {
    //         if (user.getEmail().equals(email)
    //                 && passwordEncoder.matches(password, user.getPassword())) {
    //             found = true;
    //             break;
    //         }
    //     }

    //     if (found) {
    //         model.addAttribute("ok", "いかしてるぜ");
    //         return "main";
    //     } else {
    //         model.addAttribute("error", "メールアドレスまたはパスワードが違います");
    //         return "notwelcome";
    //     }
    // }
    

    // @GetMapping("/demand")
    // public String demandPage() {
    //     return "demand";
    // }

    // @GetMapping("/forecast")
    // public String forecastPage() {
    //     return "forecast";
    // }
    //     for (Message user : users) {
    //         if (user.getEmail().equals(email)
    //                 && passwordEncoder.matches(password, user.getPassword())) {
    //             found = true;
    //             break;
    //         }
    //     }
    //     for (Message user : users) {
    //         if (user.getEmail().equals(email)
    //                 && passwordEncoder.matches(password, user.getPassword())) {
    //             found = true;
    //             break;
    //         }
    //     }

    //     if (found) {
    //         model.addAttribute("ok", "いかしてるぜ");
    //         return "main";
    //     } else {
    //         model.addAttribute("error", "メールアドレスまたはパスワードが違います");
    //         return "notwelcome";
    //     }
    // }

    @Autowired

    private ForecastService forecastService;
    // APIができたら復活　csv-forecastをコメントアウト
    @GetMapping("/forecast")
    public String getForecast(Model model) {
        ForecastResult result = forecastService.fetchForecast();
        model.addAttribute("forecast", result);
        return "forecast";
    }
    // private CsvForecastService csvForecastService;
    // @GetMapping("/csv-forecast")
    // public String showForecastFromCsv(Model model) {
    //     List<CsvForecastRecord> records = csvForecastService.loadCsvForecast();
    //     model.addAttribute("records", records);
    //     return "csv_forecast"; 
    // }// → templates/csv_forecast.html
    //APIができたらcsv-forecastをコメントアウト

    @GetMapping("/brands")
    public String brandsPage() {
        return "brands";
    }

    @GetMapping("/staff")
    public String staffPage(Model model) {
        List<Message> messages = service.getAllMessages();
        model.addAttribute("messages", messages);
        return "staff";
    }
    // @GetMapping("/staff_change")
    // public String staff_changePage() {
    //     return "staff_change";
    // }

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }
    @GetMapping("/main_user")
    public String mainUserPage() {
        return "main_user";
    }

    @GetMapping("/mainForUsers")
    public String mainPageForUsers() {
        return "mainForUsers.html";
    }
    @RequestMapping("/Performance/PerformanceView")
    public String start() {
        return "PerformanceView.html";
    }

    @RequestMapping("/Performance/PerformanceViewForUsers")
    public String startForUsers() {
        return "PerformanceViewForUsers.html";
    }

    @GetMapping("/Performance/Input")
    public String input(Model model) {
        model.addAttribute("salesData", new SalesData());
        return "Input";
    }

    @GetMapping("/sales_input")
    public String sales_inputPage() {
        return "sales_input";
    }

    @GetMapping("/sales_change")
    public String sales_changePage() {
        return "sales_change";
    }

    // スタッフ修正ページ表示
    @GetMapping("/staff_change")
    public String showStaffChange(Model model) {
        List<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "staff_change"; // 上のHTMLファイル
    }

    @PostMapping("/staff/delete")
    public String deleteStaff(@RequestParam Integer id) {
        repository.deleteById(id);
        return "redirect:/staff_change"; // 削除後に再読み込み
    }

}
