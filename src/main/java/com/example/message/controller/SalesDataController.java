package com.example.message.controller;

// 一時的にコメントアウト
/*  
import com.example.message.entity.SalesData;
import com.example.message.service.SalesDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SalesDataController {

    private final SalesDataService service;

    public SalesDataController(SalesDataService service) {
        this.service = service;
    }

    @PostMapping("/sales")
    public String showSalesData(Model model) {
    List<SalesData> salesList = service.getAllSalesData();
    model.addAttribute("salesList", salesList);
    return "sales";
    }
}
*/

import com.example.message.entity.Sales;
import com.example.message.entity.Beer;
import com.example.message.service.SalesDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalDate;

@Controller
public class SalesDataController {

    private final SalesDataService service;

    public SalesDataController(SalesDataService service) {
        this.service = service;
    }

    @PostMapping("/sales")
    public String showSalesData(Model model) {
        // 正規化されたデータを取得
        List<Sales> salesList = service.getAllSalesData();
        List<Beer> beerList = service.getAllBeers();
        
        // 日別集計データを作成（ビューで使いやすい形に加工）
        Map<LocalDate, Map<String, Object>> dailySummary = createDailySummary(salesList);
        
        model.addAttribute("salesList", salesList);
        model.addAttribute("beerList", beerList);
        model.addAttribute("dailySummary", dailySummary);
        
        return "sales";
    }

    @GetMapping("/sales")
    public String showSalesDataGet(Model model) {
        return showSalesData(model);
    }

    // 日別集計データを作成するヘルパーメソッド
    private Map<LocalDate, Map<String, Object>> createDailySummary(List<Sales> salesList) {
        return salesList.stream()
            .collect(Collectors.groupingBy(Sales::getSalesDate,
                Collectors.toMap(
                    sales -> sales.getBeer().getBeerName(),
                    sales -> Map.of(
                        "bottles", sales.getSoldBottles(),
                        "amount", sales.getSoldBottles() * sales.getBeer().getPrice()
                    ),
                    (existing, replacement) -> {
                        // 同じビール・同じ日に複数レコードがある場合の処理
                        int existingBottles = (Integer) ((Map<String, Object>) existing).get("bottles");
                        int existingAmount = (Integer) ((Map<String, Object>) existing).get("amount");
                        int newBottles = (Integer) ((Map<String, Object>) replacement).get("bottles");
                        int newAmount = (Integer) ((Map<String, Object>) replacement).get("amount");
                        
                        return Map.of(
                            "bottles", existingBottles + newBottles,
                            "amount", existingAmount + newAmount
                        );
                    }
                )
            ));
    }
}