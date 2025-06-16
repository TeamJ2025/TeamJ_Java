package com.example.message.controller;

import com.example.message.entity.SalesData;
import com.example.message.service.PerformanceService;
import com.example.message.util.ConfirmViewHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

import java.util.Map;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private ConfirmViewHelper confirmViewHelper;

    @RequestMapping("/Performance/PerformanceView")
    public String start() {
        return "PerformanceView.html";
    }

    @RequestMapping("/Performance/Input")
    public String input(Model model) {
        model.addAttribute("salesData", new SalesData());
        return "Input.html";
    }

    @PostMapping("/Performance/Confirm")
    public ModelAndView confirm(@ModelAttribute SalesData salesData) {
        ModelAndView mv = new ModelAndView("Confirm.html");

        Map<String, Integer> amounts = performanceService.calculateAmounts(salesData);
        int totalSalesYen = performanceService.calculateTotalSalesYen(amounts);
        int totalCups = performanceService.calculateTotalCount(salesData);

        confirmViewHelper.setupConfirmModel(mv, salesData, amounts, totalCups, totalSalesYen);

        return mv;
    }

    @PostMapping("/Performance/Complete")
    public String complete(@ModelAttribute("salesData") SalesData salesData) {
        performanceService.savePerformance(salesData);
        return "Complete.html";
    }
}
