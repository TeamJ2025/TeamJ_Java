package com.example.message.repository;

import com.example.message.entity.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SalesDataRepository extends JpaRepository<SalesData, LocalDate> {
    List<SalesData> findAllBySaleDayBetween(LocalDate startDate, LocalDate endDate);
    List<SalesData> findAllByOrderBySaleDayDesc();
    SalesData findBySaleDay(LocalDate saleDay);  // 特定の日付でデータを取得
}
