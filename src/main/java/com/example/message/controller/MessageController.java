package com.example.message.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
//需要予測、ダミーデータを使った挙動確認用
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.message.entity.Beer;
import com.example.message.entity.Sales;
import com.example.message.model.Message;
import com.example.message.repository.BeerRepository;
import com.example.message.repository.MessageRepository;
import com.example.message.service.ForecastService;
import com.example.message.service.MessageService;
import com.example.message.service.SalesDataService;
import com.example.message.service.SalesService;

@Controller
public class MessageController {

    private final MessageService service;
    private final MessageRepository repository;
    private final SalesService salesService;
    private final SalesDataService salesDataService;
    private ForecastService forecastService;

    public MessageController(MessageService service,
                            MessageRepository repository,
                            SalesService salesService,
                            SalesDataService salesDataService,
                            ForecastService forecastService) {
        this.service = service;
        this.repository = repository;
        this.salesService = salesService;
        this.salesDataService = salesDataService;
        this.forecastService = forecastService;
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
    

    @GetMapping("/forecast")
    public String getForecast(Model model) {
        List<Map<String, Object>> fullList = forecastService.fetchForecast();

        List<Map<String, Object>> slicedList = new ArrayList<>();
        for (int i = 1; i < fullList.size(); i++) { // 1件目はスキップ
            Map<String, Object> item = fullList.get(i);

            // 日付をパースして曜日を取得
            String dateStr = item.get("date").toString();
            LocalDate date = LocalDate.parse(dateStr.substring(0, 10));
            String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.JAPANESE);

            item.put("dayOfWeek", dayOfWeek);
            item.put("weather", ((int) item.get("weather_code") < 3) ? "晴れ" : "雨または曇り");
            item.put("reservationCount", 20);
            slicedList.add(item);

            if (dayOfWeek.equals("土曜日")) {
                break;  // 土曜日のデータまでで終了
            }
        }

        model.addAttribute("forecastList", slicedList);
        return "forecast";
    }


    // @GetMapping("/forecast")
    // public String getWeeklyForecast(Model model) {
    //     List<ForecastResult> weekForecast = new ArrayList<>();

    //     weekForecast.add(createDummy("2025-06-16", "月曜日", "晴れ", 22.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     weekForecast.add(createDummy("2025-06-16", "火曜日", "晴れ", 19.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     weekForecast.add(createDummy("2025-06-16", "水曜日", "晴れ", 22.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     weekForecast.add(createDummy("2025-06-16", "木曜日", "晴れ", 22.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     weekForecast.add(createDummy("2025-06-16", "金曜日", "晴れ", 22.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     weekForecast.add(createDummy("2025-06-16", "土曜日", "晴れ", 22.0, 10, Map.of(
    //         "pale_ale_bottles", 20, "lager_bottles", 15, "ipa_bottles", 12,"white_beer_bottles", 
    //         8,"black_beer_bottles",6,"fruit_beer_bottles",2)));

    //     model.addAttribute("forecastList", weekForecast);
    //     return "forecast";
    // }

    // private ForecastResult createDummy(String date, String day, String weather, double temp, int resCount, Map<String, Integer> items) {
    //     ForecastResult f = new ForecastResult();
    //     f.setDate(date);
    //     f.setDayOfWeek(day);
    //     f.setWeather(weather);
    //     f.setTemperature(temp);
    //     f.setReservationCount(resCount);
    //     f.setPredictedItems(items);
    //     return f;
    // }


    @GetMapping("/staff")
    public String staffPage(Model model) {
        List<Message> messages = repository.findByIsDeletedFalse(); // 変更
        model.addAttribute("messages", messages);
        return "staff";
    }
    


    @GetMapping("/main")
    public String mainPage(Authentication authentication) {
        // ログインユーザーの権限を確認して画面を分岐
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return "main";  // 管理者用画面（main.html）
        } else {
            return "main_user";  // 一般ユーザー用画面（main_user.html）
        }
    }


    @GetMapping("/main_user")
    public String mainUserPage() {
        return "main_user";
    }

    @GetMapping("/minigame")
    public String minigamePage() {
        return "minigame";
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

    @Autowired
    private BeerRepository beerRepository;
    @GetMapping("/Performance/Input")
    public String input(Model model) {
        model.addAttribute("beerList", beerRepository.findByIsDeletedFalse());
        return "Input";
    }


    @PostMapping("/Performance/Confirm")
    public String submitSalesData(
            @RequestParam("salesDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salesDate,
            @RequestParam("beerIds") List<Integer> beerIds,
            @RequestParam("quantities") List<Integer> quantities,
            Model model
    ) {
        int userId = 1;
        LocalDateTime now = LocalDateTime.now();

        List<Sales> confirmList = new ArrayList<>();
        int totalCups = 0;
        int totalSalesYen = 0;

        for (int i = 0; i < beerIds.size(); i++) {
            Integer beerId = beerIds.get(i);
            Integer quantity = quantities.get(i);

            if (quantity != null && quantity > 0) {
                Beer beer = beerRepository.findById(beerId).orElse(null);

                if (beer != null) {
                    Sales sale = new Sales(salesDate, userId, beerId, quantity);
                    sale.setBeer(beer); // これ追加！！
                    sale.setCreatedAt(now);
                    sale.setUpdatedAt(now);
                    confirmList.add(sale);

                    totalCups += quantity;
                    totalSalesYen += quantity * beer.getPrice();
                }
            }
        }

        model.addAttribute("confirmList", confirmList);
        model.addAttribute("totalCups", totalCups);
        model.addAttribute("totalSalesYen", totalSalesYen);

        return "Confirm";
    }

    @PostMapping("/Performance/Complete")
    public String completeSales(
            @RequestParam("beerIds") List<Integer> beerIds,
            @RequestParam("quantities") List<Integer> quantities
    ) {
        int userId = 1; // 仮ユーザーID
        LocalDate salesDate = LocalDate.now(); // 日付が必要なら別で渡してもOK！
        LocalDateTime now = LocalDateTime.now();

        List<Sales> salesList = new ArrayList<>();

        for (int i = 0; i < beerIds.size(); i++) {
            Integer beerId = beerIds.get(i);
            Integer quantity = quantities.get(i);

            if (quantity != null && quantity > 0) {
                Sales sale = new Sales();
                sale.setSalesDate(salesDate);
                sale.setUsersId(userId);
                sale.setBeersId(beerId);
                sale.setSoldBottles(quantity);
                sale.setCreatedAt(now);
                sale.setUpdatedAt(now);
                salesList.add(sale);
            }
        }

        salesService.saveAll(salesList);
        return "redirect:/main";
    }

    // 銘柄一覧ページ表示
    @GetMapping("/brands")
    public String showBrands(Model model) {
        model.addAttribute("beerList", beerRepository.findByIsDeletedFalse());
        model.addAttribute("newBeer", new Beer());
        return "brands"; // brands.html を表示
    }

    // 銘柄追加処理
    @PostMapping("/brands/add")
    public String addBrand(@ModelAttribute("newBeer") Beer beer) {
        beer.setIsDeleted(false);
        beerRepository.save(beer);
        return "redirect:/brands";
    }

    // 銘柄削除処理
    @PostMapping("/brands/delete/{id}")
    public String deleteBrand(@PathVariable Integer id) {
        beerRepository.findById(id).ifPresent(beer -> {
            beer.setIsDeleted(true);
            beerRepository.save(beer);
        });
        return "redirect:/brands";
    }

    @GetMapping("/sales_change")
    public String showSalesChangePage(@RequestParam int year, @RequestParam int month, Model model) {
        List<Sales> allSales = salesDataService.getAllSalesData();
 
        // 指定年月でフィルタ
        List<Sales> filtered = allSales.stream()
                .filter(s -> {
                    LocalDate date = s.getSalesDate();
                    return date.getYear() == year && date.getMonthValue() == month;
                })
                .toList();
 
        // 修正ポイント：戻り値の型と一致させる
        Map<LocalDate, Map<String, Map<String, Integer>>> dailySummary = createDailySummary(filtered);
 
        model.addAttribute("dailySummary", dailySummary);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("beerList", salesDataService.getAllBeers()); // ビール一覧（列ヘッダーに使う）
 
        return "sales_change";
    }

    @GetMapping("/message/sales_view") // 例：メッセージ画面から売上を見るとき
    public String showSalesDataInMessage(Model model) {
        List<Sales> salesList = salesDataService.getAllSalesData();
 
        // 修正：戻り値型を正しく合わせる
        Map<LocalDate, Map<String, Map<String, Integer>>> dailySummary = createDailySummary(salesList);
 
        model.addAttribute("dailySummary", dailySummary);
        model.addAttribute("year", 2025);
        model.addAttribute("month", 6);
        model.addAttribute("beerList", salesDataService.getAllBeers()); // 必要ならこれも追加
 
        return "sales"; // templates/sales.html を表示
    }
 
 
    private Map<LocalDate, Map<String, Map<String, Integer>>> createDailySummary(List<Sales> salesList) {
        return salesList.stream()
            .collect(Collectors.groupingBy(Sales::getSalesDate)) // 日付ごとにまとめる
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .collect(Collectors.toMap(
                Map.Entry::getKey, // 日付
                entry -> {
                    List<Sales> salesForDate = entry.getValue();
                    Map<String, Map<String, Integer>> beerData = new HashMap<>();
 
                    for (Sales sale : salesForDate) {
                        String beerName = sale.getBeer().getBeerName();
                        int bottles = sale.getSoldBottles();
                        int amount = bottles * sale.getBeer().getPrice();
 
                        beerData.merge(
                            beerName,
                            new HashMap<>(Map.of("bottles", bottles, "amount", amount)),
                            (existing, newEntry) -> {
                                int updatedBottles = existing.getOrDefault("bottles", 0) + newEntry.getOrDefault("bottles", 0);
                                int updatedAmount = existing.getOrDefault("amount", 0) + newEntry.getOrDefault("amount", 0);
                                existing.put("bottles", updatedBottles);
                                existing.put("amount", updatedAmount);
                                return existing;
                            }
                        );
                    }
 
                    return beerData;
                },
                (a, b) -> a,
                LinkedHashMap::new
            ));
    }

    // スタッフ管理ページ表示（論理削除されていないもののみ）    
    @GetMapping("/staff_change")    
    public String showStaffChange(Model model) {        
        try {            List<Message> messages = repository.findByIsDeletedFalse(); 
            // 変更            
            model.addAttribute("messages", messages);           
            return "staff_change";        
        } catch (Exception e) {            
            model.addAttribute("errorMessage", "データ取得エラー: " + e.getMessage());            
            model.addAttribute("messages", new ArrayList<Message>());            
            return "staff_change";        
        }    
    }

    // パスワードエンコーダーを追加
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/sales_change")
    public String updateSalesData(@RequestParam Map<String, String> allParams) {
        List<Sales> updatedSalesList = new ArrayList<>();
 
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey(); // e.g., "ペールエール__2024-04-01"
            String value = entry.getValue();
 
            if (!key.contains("__") || value.isBlank()) continue;
 
            try {
                String[] parts = key.split("__");
                if (parts.length != 2) continue;
 
                String beerName = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                int bottles = Integer.parseInt(value);
 
                // 該当するSalesデータを取得
                List<Sales> salesList = salesDataService.getSalesByDate(date).stream()
                        .filter(s -> s.getBeer().getBeerName().equals(beerName))
                        .toList();
 
                if (!salesList.isEmpty()) {
                    // 既存データがある場合は更新
                    for (Sales sale : salesList) {
                        sale.setSoldBottles(bottles);
                        sale.setUpdatedAt(LocalDateTime.now());
                        updatedSalesList.add(sale);
                    }
                } else {
                    // データが存在しない場合は新規作成
                    Sales newSale = new Sales();
                    newSale.setSalesDate(date);
                    newSale.setSoldBottles(bottles);
                    newSale.setCreatedAt(LocalDateTime.now());
                    newSale.setUpdatedAt(LocalDateTime.now());
                    newSale.setBeer(salesDataService.getBeerByName(beerName));
                    newSale.setBeersId(newSale.getBeer().getId());
                    newSale.setUsersId(1); // ※ここは適切なユーザーIDに置き換えてください
                    updatedSalesList.add(newSale);
                }
            } catch (Exception e) {
                System.err.println("変換エラー: " + key + " = " + value);
            }
        }
 
        salesService.saveAll(updatedSalesList);
        return "redirect:/sales";
    }

@GetMapping("/salesforusers")
public String salesForUsers(@RequestParam(required = false, defaultValue = "2025") int year,
                            @RequestParam(required = false, defaultValue = "1") int month,
                            Model model) {

    List<Sales> allSalesList = salesDataService.getAllSalesData();

    List<Sales> filtered = allSalesList.stream()
            .filter(s -> {
                LocalDate date = s.getSalesDate();
                return date.getYear() == year && date.getMonthValue() == month;
            })
            .toList();

    Map<LocalDate, Map<String, Map<String, Integer>>> dailySummary = createDailySummary(filtered);

    model.addAttribute("dailySummary", dailySummary);
    model.addAttribute("year", year);
    model.addAttribute("month", month);

    return "salesforusers";
    }



    // スタッフ削除処理（論理削除に変更）
    @PostMapping("/staff/delete")
    public String deleteStaff(@RequestParam Integer id) {
        repository.deleteById(id);
        return "redirect:/staff_change"; // 削除後に再読み込み
    }

    // ユーザー情報編集処理
    @PostMapping("/staff/edit")
    public String editStaff(@RequestParam Integer id,
                            @RequestParam String name,
                            @RequestParam String email,
                            @RequestParam(required = false) String password,
                            Model model) {
        try {
            // 入力値検証
            if (name == null || name.trim().isEmpty()) {
                model.addAttribute("errorMessage", "名前は必須です。");
                List<Message> messages = repository.findAll();
                model.addAttribute("messages", messages);
                return "staff_change";
            }
            
            if (email == null || email.trim().isEmpty()) {
                model.addAttribute("errorMessage", "メールアドレスは必須です。");
                List<Message> messages = repository.findAll();
                model.addAttribute("messages", messages);
                return "staff_change";
            }

            Message user = repository.findById(id).orElse(null);
            if (user != null) {
                user.setName(name.trim());
                user.setEmail(email.trim());
                
                // パスワードが入力されている場合のみ更新
                if (password != null && !password.trim().isEmpty()) {
                    user.setPassword(passwordEncoder.encode(password));
                }
                
                user.setUpdatedAt(LocalDateTime.now());
                repository.save(user);
                model.addAttribute("successMessage", "ユーザー情報を更新しました。");
            } else {
                model.addAttribute("errorMessage", "ユーザーが見つかりません。");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "更新に失敗しました: " + e.getMessage());
        }
        
        List<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "staff_change";
    }

    // 権限変更処理
    @PostMapping("/staff/role")
    public String changeRole(@RequestParam Integer id,
                            @RequestParam String newRole,
                            Model model) {
        try {
            // 入力値検証（実際の権限値 'admin' と 'user' に対応）
            if (!("admin".equals(newRole) || "user".equals(newRole))) {
                model.addAttribute("errorMessage", "無効な権限です。");
                List<Message> messages = repository.findAll();
                model.addAttribute("messages", messages);
                return "staff_change";
            }

            Message user = repository.findById(id).orElse(null);
            if (user != null) {
                user.setRole(newRole);
                user.setUpdatedAt(LocalDateTime.now());
                repository.save(user);
                
                String roleDisplayName = "admin".equals(newRole) ? "管理者" : "従業員";
                model.addAttribute("successMessage", 
                    user.getName() + "さんの権限を" + roleDisplayName + "に変更しました。");
            } else {
                model.addAttribute("errorMessage", "ユーザーが見つかりません。");
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", "権限変更に失敗しました: " + e.getMessage());
        }
        
        List<Message> messages = repository.findAll();
        model.addAttribute("messages", messages);
        return "staff_change";
    }

} 