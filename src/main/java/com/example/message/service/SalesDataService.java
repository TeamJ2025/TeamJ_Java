package com.example.message.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.message.entity.Beer;

// 一時的にコメントアウト
/*
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
*/

import com.example.message.entity.Sales;
import com.example.message.repository.BeerRepository;
import com.example.message.repository.SalesRepository;


@Service
public class SalesDataService {

    private final SalesRepository salesRepository;
    private final BeerRepository beerRepository;

    public SalesDataService(SalesRepository salesRepository, BeerRepository beerRepository) {
        this.salesRepository = salesRepository;
        this.beerRepository = beerRepository;
    }

    // 全ての販売データを取得（日付降順）- 既存のfindAllByOrderBySaleDayDesc()に対応
    public List<Sales> getAllSalesData() {
        return salesRepository.findAllByOrderBySalesDateDesc();
    }

    // 全てのビール情報を取得
    public List<Beer> getAllBeers() {
        return beerRepository.findByIsDeletedFalse();
    }

    // ビール情報とユーザー情報を結合した販売データを取得
    public List<Sales> getAllSalesWithDetails() {
        return salesRepository.findAll(); // JOIN FETCHでビール・ユーザー情報も取得
    }
    public List<Sales> getSalesByDate(LocalDate date) {
        return salesRepository.findBySalesDate(date);
    }
    public void saveSales(Sales sales) {
        salesRepository.save(sales);
    }
        public Beer getBeerByName(String beerName) {
        return beerRepository.findByBeerNameAndIsDeletedFalse(beerName)
                .orElseThrow(() -> new RuntimeException("指定されたビール名が見つかりません: " + beerName));
    }
    public void saveAll(List<Sales> salesList) {
        salesRepository.saveAll(salesList);
    }
 
}