package com.example.message.controller;

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
    public String showSalesData(@RequestParam(value = "year", defaultValue = "2025") int year,
                                 @RequestParam(value = "month", defaultValue = "1") int month, 
                                 Model model) {
        List<SalesData> salesList = service.getSalesDataByMonth(year, month);
        model.addAttribute("salesList", salesList);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        return "sales"; 
    }

    // 1ヶ月分のデータを一括修正ページに表示
    @GetMapping("/sales_change_all")
    public String showSalesDataToChangeAll(@RequestParam("year") int year,
                                           @RequestParam("month") int month, 
                                           Model model) {
        List<SalesData> salesList = service.getSalesDataByMonth(year, month);
        model.addAttribute("salesList", salesList);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        return "sales_change_all";  // 新しい修正ページ
    }

    // 修正内容を保存（1ヶ月分）
    @PostMapping("/sales_change")
    public String updateSalesDataAll(List<SalesData> updatedDataList, Model model) {
        for (SalesData updatedData : updatedDataList) {
            service.updateSalesData(updatedData);  // データを保存
        }
        model.addAttribute("message", "全てのデータが修正されました");
        return "redirect:/sales";  // 修正後に売上データ一覧に戻る
    }
}
