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


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.message.entity.Beer;
import com.example.message.entity.Sales;
import com.example.message.entity.WeatherData;
import com.example.message.repository.BeerRepository;
import com.example.message.repository.SalesRepository;
import com.example.message.repository.WeatherDataRepository;

@Service
public class SalesDataService {

    private final SalesRepository salesRepository;
    private final BeerRepository beerRepository;
    private final WeatherDataRepository weatherDataRepository;

    public SalesDataService(SalesRepository salesRepository,
                            BeerRepository beerRepository,
                            WeatherDataRepository weatherDataRepository) {
        this.salesRepository = salesRepository;
        this.beerRepository = beerRepository;
        this.weatherDataRepository = weatherDataRepository;
    }

    // 全ての販売データを日付降順で取得
    public List<Sales> getAllSalesData() {
        return salesRepository.findAllByOrderBySalesDateDesc();
    }

    // 削除フラグが立っていないビールデータを取得
    public List<Beer> getAllBeers() {
        return beerRepository.findByIsDeletedFalse();
    }

    // 指定した日付の販売データを取得
    public List<Sales> getSalesByDate(LocalDate date) {
        return salesRepository.findBySalesDate(date);
    }

    // 単一の販売データを保存
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
 

    // 指定した日付の天気情報を1件取得（Optionalで返す）
    public Optional<WeatherData> getWeatherByDate(LocalDate date) {
        return weatherDataRepository.findByObservationDate(date);
    }

    // ★ 複数日付の天気情報を一括で取得する（パフォーマンス改善用）
    public List<WeatherData> getWeatherDataByDates(List<LocalDate> dates) {
        return weatherDataRepository.findByObservationDateIn(dates);
    }
}
