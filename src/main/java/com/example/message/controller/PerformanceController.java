package com.example.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import com.example.message.been.PerformanceBeen;
import com.example.message.service.PerformanceService;
import com.example.message.util.ConfirmViewHelper;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PerformanceController {
    
    @Autowired
    private PerformanceService performanceService;

    @RequestMapping("/Performance/PerformanceView")
    public String start(){
        return "PerformanceView.html";
    }
    
    @RequestMapping("/Performance/Input")
    public String input(){
        return "Input.html";
    }

    @Autowired
    private ConfirmViewHelper confirmViewHelper;

    @PostMapping("/Performance/Confirm")
    public ModelAndView confirm(@ModelAttribute PerformanceBeen been){
        ModelAndView mv = new ModelAndView("Confirm.html");

        Map<String, Integer> amounts = performanceService.calculateAmounts(been);
        int totalSalesYen = performanceService.calculateTotalAmount(amounts);
        int totalCups = performanceService.calculateTotalCount(been);

        confirmViewHelper.setupConfirmModel(mv, been, amounts, totalCups, totalSalesYen);

        return mv;
    }
    @PostMapping("/Performance/Complete")
    public String complete(@ModelAttribute("been") PerformanceBeen been) {
        performanceService.savePerformance(been); // ここでDB保存
        return "Complete.html";
    }
}
