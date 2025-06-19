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
import org.springframework.format.annotation.DateTimeFormat;

import com.example.message.model.CsvForecastRecord;
import com.example.message.service.CsvForecastService;
import com.example.message.entity.Sales;
import com.example.message.entity.SalesData;
import java.util.List;

import com.example.message.service.SalesService;
import java.time.LocalDate;
import java.time.LocalDateTime;

//需要予測用
import com.example.message.model.ForecastResult;
import com.example.message.service.ForecastService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
//需要予測、ダミーデータを使った挙動確認用
import java.util.HashMap;
import java.util.Map;

@Controller
public class MessageController {

    private final MessageService service;
    private final MessageRepository repository;
    private final SalesService salesService;  // ★ final を付ける

    public MessageController(MessageService service,
                             MessageRepository repository,
                             SalesService salesService) {
        this.service = service;
        this.repository = repository;
        this.salesService = salesService;  // ★ ここで注入される
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
    // @GetMapping("/forecast")
    // public String getForecast(Model model) {
    //     ForecastResult result = forecastService.fetchForecast();
    //     model.addAttribute("forecast", result);
    //     return "forecast";
    // }

@GetMapping("/forecast")
public String getWeeklyForecast(Model model) {
    List<ForecastResult> weekForecast = new ArrayList<>();

    weekForecast.add(createDummy("2025-06-16", "月曜日", "晴れ", 22.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    weekForecast.add(createDummy("2025-06-16", "火曜日", "晴れ", 19.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    weekForecast.add(createDummy("2025-06-16", "水曜日", "晴れ", 22.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    weekForecast.add(createDummy("2025-06-16", "木曜日", "晴れ", 22.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    weekForecast.add(createDummy("2025-06-16", "金曜日", "晴れ", 22.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    weekForecast.add(createDummy("2025-06-16", "土曜日", "晴れ", 22.0, 10, Map.of(
        "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
        8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    model.addAttribute("forecastList", weekForecast);
    return "forecast";
}

private ForecastResult createDummy(String date, String day, String weather, double temp, int resCount, Map<String, Integer> items) {
    ForecastResult f = new ForecastResult();
    f.setDate(date);
    f.setDayOfWeek(day);
    f.setWeather(weather);
    f.setTemperature(temp);
    f.setReservationCount(resCount);
    f.setPredictedItems(items);
    return f;
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

    @PostMapping("/Performance/Confirm")
    public String submitSalesData(
            @RequestParam("salesDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salesDate,
            @RequestParam(value = "whiteBeerBottles", required = false) Integer white,
            @RequestParam(value = "lagerBottles", required = false) Integer lager,
            @RequestParam(value = "paleAleBottles", required = false) Integer pale,
            @RequestParam(value = "fruitBeerBottles", required = false) Integer fruit,
            @RequestParam(value = "blackBeerBottles", required = false) Integer black,
            @RequestParam(value = "ipaBottles", required = false) Integer ipa
    ) {
        int userId = 1; // 仮設定。ログイン中のユーザーに変更可能
        LocalDateTime now = LocalDateTime.now();

        List<Sales> salesList = new ArrayList<>();
        if (white != null && white > 0) salesList.add(new Sales(salesDate, userId, 1, white));
        if (lager != null && lager > 0) salesList.add(new Sales(salesDate, userId, 2, lager));
        if (pale != null && pale > 0)  salesList.add(new Sales(salesDate, userId, 3, pale));
        if (fruit != null && fruit > 0) salesList.add(new Sales(salesDate, userId, 4, fruit));
        if (black != null && black > 0) salesList.add(new Sales(salesDate, userId, 5, black));
        if (ipa != null && ipa > 0)    salesList.add(new Sales(salesDate, userId, 6, ipa));

        for (Sales sale : salesList) {
            sale.setCreatedAt(now);
            sale.setUpdatedAt(now);
        }

        salesService.saveAll(salesList);
        return "redirect:/Performance/Input?success";
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
