package com.example.message.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.message.entity.Beer;

// 一時的にコメントアウト
/*  
import com.example.message.entity.SalesData;
import com.example.message.service.SalesDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SalesDataController {

    private final SalesDataService service;

    public SalesDataController(SalesDataService service) {
        this.service = service;
    }

    @GetMapping("/sales")
    public String showSalesData(Model model) {
        List<SalesData> salesList = service.getAllSalesData();
        model.addAttribute("salesList", salesList);
        return "sales"; 
    }

    @PostMapping("/sales")
    public String handlePostSalesData(Model model) {
        List<SalesData> salesList = service.getAllSalesData();
        model.addAttribute("salesList", salesList);
        return "sales"; 
    }
}
*/

import com.example.message.entity.Sales;
import com.example.message.repository.MessageRepository;
import com.example.message.service.MessageService;
import com.example.message.service.SalesDataService;
import com.example.message.service.SalesService;

@Controller
public class SalesDataController {

    private final SalesDataService service;

    public SalesDataController(SalesDataService service) {
        this.service = service;
    }

    @PostMapping("/sales")
    public String showSalesData(Model model) {
        List<Sales> salesList = service.getAllSalesData();
        List<Beer> beerList = service.getAllBeers();

        Map<LocalDate, Map<String, Object>> dailySummary = createDailySummary(salesList);

        model.addAttribute("salesList", salesList);
        model.addAttribute("beerList", beerList);
        model.addAttribute("dailySummary", dailySummary);

        return "sales";
    }

    // @GetMapping("/sales")
    // public String showSalesDataGet(Model model) {
    //     return showSalesData(model);
    // }
    // @GetMapping("/Performance/Input")
    // public String input(Model model) {
    //     List<Sales> allSalesList = service.getAllSalesData();
    //     model.addAttribute("salesData", allSalesList);
    //     return "Input";
    // }

    @GetMapping("/sales_edit")
    public String editSalesForDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                    Model model) {
        List<Sales> salesList = service.getSalesByDate(date);
        model.addAttribute("date", date);
        model.addAttribute("salesList", salesList);
        return "sales_edit";
    }


    @GetMapping("/sales")
    public String showSalesDataGet(
            @RequestParam(required = false, defaultValue = "2024") int year,
            @RequestParam(required = false, defaultValue = "1") int month,
            Model model) {

        List<Sales> allSalesList = service.getAllSalesData();

        List<Sales> filtered = allSalesList.stream()
                .filter(s -> {
                    LocalDate date = s.getSalesDate();
                    return date.getYear() == year && date.getMonthValue() == month;
                })
                .toList();

        Map<LocalDate, Map<String, Object>> dailySummary = createDailySummary(filtered);
        
        //修正するところ
        LocalDate firstDate = LocalDate.of(year, month, 1);
        LocalDate lastDate = firstDate.withDayOfMonth(firstDate.lengthOfMonth());

        // 全日付をループして、データがなければ空のエントリを補完
        Map<LocalDate, Map<String, Object>> completeSummary = new LinkedHashMap<>();
        for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusDays(1)) {
            if (dailySummary.containsKey(date)) {
                completeSummary.put(date, dailySummary.get(date));
            } else {
                completeSummary.put(date, Map.of(
                    "beerData", new HashMap<>(),
                    "totalBottles", 0,
                    "totalAmount", 0
                ));
            }
        }
        // 日付順に並んだ List<Entry<LocalDate, Map<String, Object>>> を作成
        List<Map.Entry<LocalDate, Map<String, Object>>> orderedSummary = new ArrayList<>(completeSummary.entrySet());


        int dayOfWeekIndex = firstDate.getDayOfWeek().getValue() % 7;

        // ✅ ログ確認用（デバッグ）
        System.out.println("firstDate = " + firstDate + ", dayOfWeek = " + firstDate.getDayOfWeek() + ", index = " + dayOfWeekIndex);


        model.addAttribute("dayOfWeekIndex", dayOfWeekIndex);


        model.addAttribute("firstDate", firstDate);
        model.addAttribute("dailySummaryList", orderedSummary);

        model.addAttribute("year", year);
        model.addAttribute("month", month);

        return "sales";
    }


    private Map<LocalDate, Map<String, Object>> createDailySummary(List<Sales> salesList) {
        return salesList.stream()
            .collect(Collectors.groupingBy(Sales::getSalesDate))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey()) // 昇順：1月1日が最初にくる
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> {
                    // 集計処理はそのまま
                    LocalDate date = entry.getKey();
                    List<Sales> salesForDate = entry.getValue();

                    Map<String, Map<String, Integer>> beerData = new HashMap<>();
                    int totalBottles = 0;
                    int totalAmount = 0;

                    for (Sales sale : salesForDate) {
                        String beerName = sale.getBeer().getBeerName();
                        int bottles = sale.getSoldBottles();
                        int amount = bottles * sale.getBeer().getPrice();

                        beerData.merge(beerName,
                            Map.of("bottles", bottles, "amount", amount),
                            (existing, newEntry) -> Map.of(
                                "bottles", existing.get("bottles") + newEntry.get("bottles"),
                                "amount", existing.get("amount") + newEntry.get("amount")
                            ));

                        totalBottles += bottles;
                        totalAmount += amount;
                    }

                    return Map.of(
                        "beerData", beerData,
                        "totalBottles", totalBottles,
                        "totalAmount", totalAmount
                    );
                },
                (a, b) -> a, // マージ戦略：重複なし
                LinkedHashMap::new // 昇順を保持
            ));
    }
}