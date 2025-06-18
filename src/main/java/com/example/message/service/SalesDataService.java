package com.example.message.service;

import com.example.message.entity.SalesData;
import com.example.message.repository.SalesDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesDataService {

    private final SalesDataRepository repository;

    public SalesDataService(SalesDataRepository repository) {
        this.repository = repository;
    }

    public List<SalesData> getSalesDataByMonth(int year, int month) {
        // 月ごとのデータを取得するメソッド（例: 月初日から月末日まで）
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return repository.findAllBySaleDayBetween(startDate, endDate);
    }

    public SalesData getSalesDataByDate(LocalDate saleDay) {
        return repository.findBySaleDay(saleDay);  // 特定の日付でデータを取得
    }

    public void updateSalesData(SalesData updatedData) {
        repository.save(updatedData);  // 更新されたデータを保存
    }
}
