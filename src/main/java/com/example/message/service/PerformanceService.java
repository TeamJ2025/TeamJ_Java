package com.example.message.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.message.been.PerformanceBeen;
import com.example.message.entity.SalesData;
import com.example.message.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    public Map<String, Integer> calculateAmounts(PerformanceBeen been) {
        Map<String, Integer> amounts = new HashMap<>();
        amounts.put("white", been.getWhiteBeerBottles() * 900);
        amounts.put("lager", been.getLagerBottles() * 800);
        amounts.put("pale", been.getPaleAleBottles() * 1000);
        amounts.put("fruit", been.getFruitBeerBottles() * 1000);
        amounts.put("black", been.getBlackBeerBottles() * 1200);
        amounts.put("ipa", been.getIpaBottles() * 900);
        return amounts;
    }

    public int calculateTotalAmount(Map<String, Integer> amounts) {
        return amounts.values().stream().mapToInt(i -> i).sum();
    }

    public int calculateTotalCount(PerformanceBeen been) {
        return been.getWhiteBeerBottles() +
               been.getLagerBottles() +
               been.getPaleAleBottles() +
               been.getFruitBeerBottles() +
               been.getBlackBeerBottles() +
               been.getIpaBottles();
    }

    public SalesData mapToEntity(PerformanceBeen been, int totalCups, int totalSalesYen) {
        SalesData entity = new SalesData();

        entity.setSaleDay(LocalDate.now());
        entity.setTotalCups(totalCups);
        entity.setTotalSalesYen(totalSalesYen);

        entity.setWhiteBeerBottles(been.getWhiteBeerBottles());
        entity.setWhiteBeerYen(been.getWhiteBeerBottles() * 900);

        entity.setLagerBottles(been.getLagerBottles());
        entity.setLagerYen(been.getLagerBottles() * 800);

        entity.setPaleAleBottles(been.getPaleAleBottles());
        entity.setPaleAleYen(been.getPaleAleBottles() * 1000);

        entity.setFruitBeerBottles(been.getFruitBeerBottles());
        entity.setFruitBeerYen(been.getFruitBeerBottles() * 1000);

        entity.setBlackBeerBottles(been.getBlackBeerBottles());
        entity.setBlackBeerYen(been.getBlackBeerBottles() * 1200);

        entity.setIpaBottles(been.getIpaBottles());
        entity.setIpaYen(been.getIpaBottles() * 900);

        // 必要に応じて追加項目
        // entity.setReservationCount(0);
        // entity.setReservationPeople(0);
        // entity.setVisitors(0);

        return entity;
    }

    public void savePerformance(PerformanceBeen been) {
        Map<String, Integer> amounts = calculateAmounts(been);
        int totalSalesYen = calculateTotalAmount(amounts);
        int totalCups = calculateTotalCount(been);

        SalesData entity = mapToEntity(been, totalCups, totalSalesYen);
        performanceRepository.save(entity);
    }
}
