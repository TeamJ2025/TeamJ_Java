// package com.example.message.controller;

// import com.example.message.entity.SalesData;
// import com.example.message.service.SalesDataService;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.util.List;

// @Controller
// public class SalesDataController {

//     private final SalesDataService service;

//     public SalesDataController(SalesDataService service) {
//         this.service = service;
//     }

//     @GetMapping("/sales")
//     public String showSalesData(Model model) {
//         List<SalesData> salesList = service.getAllSalesData();
//         model.addAttribute("salesList", salesList);
//         return "sales";
//     }
// }
