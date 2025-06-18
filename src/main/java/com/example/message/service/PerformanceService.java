package com.example.message.service;

// 一時的にコメントアウト
/* 
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.message.entity.SalesData;
import com.example.message.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Map<String, Integer> calculateAmounts(SalesData data) {
        Map<String, Integer> amounts = new HashMap<>();
        amounts.put("white", data.getWhiteBeerBottles() * 900);
        amounts.put("lager", data.getLagerBottles() * 800);
        amounts.put("pale", data.getPaleAleBottles() * 1000);
        amounts.put("fruit", data.getFruitBeerBottles() * 1000);
        amounts.put("black", data.getBlackBeerBottles() * 1200);
        amounts.put("ipa", data.getIpaBottles() * 900);
        return amounts;
    }

    public int calculateTotalSalesYen(Map<String, Integer> amounts) {
        return amounts.values().stream().mapToInt(i -> i).sum();
    }

    public int calculateTotalCount(SalesData data) {
        return data.getWhiteBeerBottles()
             + data.getLagerBottles()
             + data.getPaleAleBottles()
             + data.getFruitBeerBottles()
             + data.getBlackBeerBottles()
             + data.getIpaBottles();
    }

    public SalesData enrichEntity(SalesData data, int totalCups, int totalSalesYen) {
        data.setSaleDay(LocalDate.now());
        data.setTotalCups(totalCups);
        data.setTotalSalesYen(totalSalesYen);
        data.setWhiteBeerYen(data.getWhiteBeerBottles() * 900);
        data.setLagerYen(data.getLagerBottles() * 800);
        data.setPaleAleYen(data.getPaleAleBottles() * 1000);
        data.setFruitBeerYen(data.getFruitBeerBottles() * 1000);
        data.setBlackBeerYen(data.getBlackBeerBottles() * 1200);
        data.setIpaYen(data.getIpaBottles() * 900);

        return data;
    }

    public void savePerformance(SalesData data) {
        Map<String, Integer> amounts = calculateAmounts(data);
        int totalSalesYen = calculateTotalSalesYen(amounts);
        int totalCups = calculateTotalCount(data);

        SalesData entity = enrichEntity(data, totalCups, totalSalesYen);
        performanceRepository.save(entity);
    }
}
*/
